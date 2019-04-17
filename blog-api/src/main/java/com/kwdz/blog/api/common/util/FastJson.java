package com.kwdz.blog.api.common.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
public class FastJson {

    private static ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 获取JSON
     */
    public static String toJsonStr(Object src) {
        try {
            MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return MAPPER.writeValueAsString(src);
        } catch (Exception e) {
            log.error("获取JSON串报错！", e);
            return null;
        }
    }

    /**
     * 解析JSON
     */
    public static <T> T parse(String jsonStr, Class<T> targetType) {
        try {
            MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return MAPPER.readValue(jsonStr, targetType);
        } catch (IOException e) {
            log.error("解析JSON串报错！", e);
            return null;
        }
    }

    /**
     * 解析JSON为List<Vo>
     */
    public static <T> List<T> parseList(String jsonStr, Class<T> targetType) {
        try {
            return new ObjectMapper().readValue(jsonStr, TypeFactory.defaultInstance().constructCollectionType(List.class, targetType));
        } catch (IOException e) {
            log.error("解析JSON为List<Vo>时报错", e);
        }
        return null;
    }
}
