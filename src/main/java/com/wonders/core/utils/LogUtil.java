package com.wonders.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {

	public static Logger getLogger(Class<?> clazz) {
		return LoggerFactory.getLogger(clazz);
	}

	public static Logger getLogger(String name) {
		return LoggerFactory.getLogger(name);
	}

	public static Logger getSystemLogger() {
		return getLogger("SYSTEM");
	}

}