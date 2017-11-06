package com.wonders.test.listener;

import com.wonders.test.webservice.WeatherImpl;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;
import javax.xml.ws.Endpoint;

/**
 * Description:
 * <p>
 * Created by liqingdong on 2017/9/22.
 */
public class MyListener extends ContextLoaderListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        String address = "http://localhost:8080/weather";
        Endpoint.publish(address, new WeatherImpl());
        super.contextInitialized(event);
    }
}
