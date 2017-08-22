package com.wonders.core.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class XssFilter implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("XssFilter初始化");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
		chain.doFilter(new XssHttpServletRequestWrapper(
				(HttpServletRequest) request), response);
	}

	@Override
	public void destroy() {
	}
}
