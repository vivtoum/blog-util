package com.kwdz.blog.svc.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * JSON工具类
 *
 * @author chenSir
 * @date 2018-08-28
 */
@Slf4j
public class JsonHelper {


    ObjectMapper mapper;

    public JsonHelper() {
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public JsonHelper(JsonInclude.Include include) {
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(include);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 对象转JSON
     *
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    public String toJson(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    public <T> T fromJson(String json, Class<T> cls) throws IOException {
        return mapper.readValue(json, cls);
    }

    public <T> T fromJson(String json, TypeReference valueTypeRef) throws IOException {
        return mapper.readValue(json, valueTypeRef);
    }
}
