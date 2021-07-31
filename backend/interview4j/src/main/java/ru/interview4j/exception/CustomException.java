package ru.interview4j.exception;
/*
 * Date: 31.07.2021
 * Time: 1:00 PM
 * */

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CustomException extends ResponseStatusException {

    public CustomException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public CustomException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    public static CustomException badRequest(String reason) {
        return new CustomException(HttpStatus.BAD_REQUEST, reason);
    }

    public static CustomException notFound(String reason) {
        return new CustomException(HttpStatus.NOT_FOUND, reason);
    }

    public static CustomException unprocessableEntity() {
        return new CustomException(HttpStatus.UNPROCESSABLE_ENTITY, "Username already in use");
    }

}
