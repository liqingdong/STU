package com.wonders.core.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class CustomJacksonObjectMapper extends ObjectMapper {

    private static final long serialVersionUID = -6771300049520742435L;

    public CustomJacksonObjectMapper() {
        super();
        this.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false);
        this.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        this.setSerializationInclusion(Include.ALWAYS);
        this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        // json中存在字段，但JAVA中没有的
        this.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {

            @Override
            public void serialize(Object value, JsonGenerator jgen,
                                  SerializerProvider provider) throws IOException,
                    JsonProcessingException {
                jgen.writeString("");
            }

        });
    }

}
