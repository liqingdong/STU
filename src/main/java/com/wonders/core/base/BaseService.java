package com.wonders.core.base;

import com.wonders.core.utils.BeanGenericUtil;
import com.wonders.core.utils.SecurityContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

public abstract class BaseService<T extends IDEntity, M extends MapperExtension<T>> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @AutowiredGenericType
    protected M mapper;

    public List<T> select(T record) {
        return mapper.select(record);
    }

    public List<T> selectAll() {
        try {
            return select((T) BeanGenericUtil.getGenericClass(getClass()).newInstance());
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public T selectOne(T record) {
        return mapper.selectOne(record);
    }

    public T selectOneOnly(T record) {
        List<T> list = select(record);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    public int selectCount(T record) {
        return mapper.selectCount(record);
    }

    public T selectByPrimaryKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    /**
     * 根据id进行更新或者插入操作
     *
     * @param record
     * @return
     */
    public int saveOrUpdate(T record) {
        if (record.getId() == null) {
            return insert(record);
        } else {
            return updateByPrimaryKeySelective(record);
        }
    }

    public int insert(T record) {
        record.setCreateDate(new Date());
        if(StringUtils.isEmpty(record.getCreateBy())){
            record.setCreateBy(SecurityContextUtil.getUsername());
        }
        return mapper.insert(record);
    }

    public int insertList(List<T> recordList) {
        return mapper.insertList(recordList);
    }

    public int insertUseGeneratedKeysMapper(T record) {
        return mapper.InsertUseGeneratedKeysMapper(record);
    }

    public int insertSelective(T record) {
        record.setCreateDate(new Date());
        if(StringUtils.isEmpty(record.getCreateBy())){
            record.setCreateBy(SecurityContextUtil.getUsername());
        }
        return mapper.insertSelective(record);
    }

    public int delete(T record) {
        return mapper.delete(record);
    }

    public int deleteByPrimaryKey(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    public int updateByPrimaryKey(T record) {
        record.setUpdateDate(new Date());
        if(StringUtils.isEmpty(record.getUpdateBy())){
            record.setUpdateBy(SecurityContextUtil.getUsername());
        }
        return mapper.updateByPrimaryKey(record);
    }

    public int updateByPrimaryKeySelective(T record) {
        record.setUpdateDate(new Date());
        if(StringUtils.isEmpty(record.getUpdateBy())){
            record.setUpdateBy(SecurityContextUtil.getUsername());
        }
        return mapper.updateByPrimaryKeySelective(record);
    }

    public int selectCountByExample(Object example) {
        return mapper.selectCountByExample(example);
    }

    public int deleteByExample(Object example) {
        return mapper.deleteByExample(example);
    }

    public List<T> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    public int updateByExampleSelective(T record, Object example) {
        return mapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(T record, Object example) {
        return mapper.updateByExample(record, example);
    }

}
