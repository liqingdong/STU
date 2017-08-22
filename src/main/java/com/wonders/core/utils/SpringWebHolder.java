package com.wonders.core.utils;

import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SpringWebHolder extends HandlerInterceptorAdapter {
    private static ThreadLocal<WebInfo> threadLocal = new NamedThreadLocal<WebInfo>("SpringWebHolder");
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        WebInfo webInfo=new WebInfo(request,response);
        threadLocal.set(webInfo);
        return super.preHandle(request, response, handler);
    }
    public static HttpServletRequest getRequest() {
      return threadLocal.get().getRequest();
    }

    public static HttpServletResponse getResponse() {
        return threadLocal.get().getResponse();
    }

    public static HttpSession getSession() {
        return threadLocal.get().getRequest().getSession();
    }

   private class WebInfo{
       private HttpServletRequest request;
       private HttpServletResponse response;
       public WebInfo(HttpServletRequest request,HttpServletResponse response){
           this.request=request;
           this.response=response;
       }

       public HttpServletRequest getRequest() {
          return this.request;
       }

       public HttpServletResponse getResponse() {
           return this.response;
       }
   }
}
