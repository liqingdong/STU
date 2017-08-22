package com.wonders.core.base;

import com.github.abel533.mapper.Mapper;
import com.github.abel533.mapper.MySqlMapper;

public interface MapperExtension<T> extends 
		Mapper<T>,
		MySqlMapper<T>{
}
