package com.wonders.core.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * springMVC返回json时间格式化 解决SpringMVC使用@ResponseBody返回json时，日期格式默认显示为时间戳的问题。
 * 需要在get方法上加上@JsonSerialize(using=JsonDateSerializer.class)
 */
public class JsonDateSerializer extends JsonSerializer<Date> {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider) throws IOException {

        String formattedDate = dateFormat.format(date);

        gen.writeString(formattedDate);
    }
}
