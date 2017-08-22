package com.wonders.core.base;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AutowiredProcessor implements BeanPostProcessor,
        ApplicationContextAware, Ordered {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * 获取指定的类型获取泛型类型，如果不存在则返回null
	 * 
	 * @param clazz
	 * @param type
	 * @return
	 */
	private Class<?> getGenericClass(Class<?> clazz, Class<?> type) {
		List<Type> types = new ArrayList<Type>();
		for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
			types.add(clazz.getGenericSuperclass());
		}
		for (Iterator<Type> iterator = types.iterator(); iterator.hasNext();) {
			Type type2 = iterator.next();
			if (type2 instanceof ParameterizedType) {
				Type[] p = ((ParameterizedType) type2).getActualTypeArguments();
				for (Type type3 : p) {
					if (type.isAssignableFrom((Class<?>) type3)) {
						return (Class<?>) type3;
					}
				}
			}
		}
		return null;
	}

	private int order = 99;

	public int getOrder() {
		return this.order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public Object postProcessBeforeInitialization(final Object bean, String beanName)
			throws BeansException {
		ReflectionUtils.doWithFields(bean.getClass(),
				new ReflectionUtils.FieldCallback() {

					@Override
					public void doWith(Field field)
							throws IllegalArgumentException,
							IllegalAccessException {
						AutowiredGenericType autowired = field
								.getAnnotation(AutowiredGenericType.class);
						if (autowired != null) {
							Class<?> genericClass = getGenericClass(
									bean.getClass(), field.getType());
							if (genericClass == null) {
								throw new BeanCreationException(
										"Could not autowire field " + field
												+ ", no genericClass found");
							}
							Object object = applicationContext
									.getBean(genericClass);
							if (object == null && autowired.required()) {
								throw new BeanCreationException(
										"Could not autowire field " + field
												+ ", no bean of "
												+ genericClass + " found");
							}
							if (!field.isAccessible()) {
								field.setAccessible(true);
							}
							Object o = field.get(bean);
							if(o == null){
								field.set(bean, object);
							}
						}
					}

				});
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean,
			String beanName) throws BeansException {
		return bean;
	}

}
