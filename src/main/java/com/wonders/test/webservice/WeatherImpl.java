package com.wonders.test.webservice;

import javax.jws.WebService;

/**
 * Description:
 * <p>
 * Created by liqingdong on 2017/9/22.
 */
@WebService
public class WeatherImpl implements Weather {
    
    public String getWeather() {
        return "今日天气:晴,东南风2~4级";
    }
}
