package com.example.pcv.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static java.util.stream.Collectors.joining;

import org.apache.commons.beanutils.PropertyUtils;

public class ObjectMapperUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String convertToUrlEncoded(Object obj) {
        Map<String, String> map = objectMapper.convertValue(obj, Map.class);

        return map.keySet().stream()
                .map(key -> {
                    try {
                        Class typeClass = PropertyUtils.getPropertyType(obj, key);
                        String type = typeClass.getSimpleName();
                        String value = null;

                        if (type.equals("String")) {
                            value = map.get(key);
                        } else if (typeClass.isPrimitive()) {
                            value = String.valueOf(map.get(key));
                        }

                        return value != null && value.length() > 0
                                ? key + "=" + URLEncoder.encode(value, StandardCharsets.UTF_8.toString())
                                : null;
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();

                        throw new UnsupportedOperationException(); // ???
                    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        e.printStackTrace();
                    }

                    return null;
                })
                .filter(value -> value != null)
                .collect(joining("&"));
    }
}
