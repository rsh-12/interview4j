package ru.interview4j.exception;
/*
 * Date: 31.07.2021
 * Time: 12:59 PM
 * */

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Map<String, Object> map = super.getErrorAttributes(request, options);

        if (getError(request) instanceof CustomException exception) {
            map.put("message", exception.getMessage());
            map.put("status", exception.getStatus().value());
            return map;
        }

        map.put("message", "Something went wrong");
        map.put("status", "500");
        map.put("error", "Internal server error");
        return map;
    }
}
