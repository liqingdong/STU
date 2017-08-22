package com.wonders.core.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wonders.core.frame.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public abstract class AbstractInterceptor extends HandlerInterceptorAdapter {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	protected ObjectMapper objectMapper;
	
	protected void output(String message , HttpServletResponse response){
		output(message, response , HttpStatus.OK);
	}
	
	protected void output(String message , HttpServletResponse response , HttpStatus status){
		CommonResult<?> result = new CommonResult<>();
		result.setMessage(message);
		output(response, result, status);
	}
	
	protected void output(HttpServletResponse response , CommonResult<?> result , HttpStatus status){
		try {
			// 设置状态码
			response.setStatus(status.value());
			// 设置返回值
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			// 返回值编码
			response.setCharacterEncoding("UTF-8");
			
			result.setSuccess(false);
			PrintWriter writer = response.getWriter();
			writer.write(objectMapper.writeValueAsString(result));
			writer.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	protected void output(HttpServletResponse response , CommonResult<?> result){
		output(response, result, HttpStatus.OK);
	}

}