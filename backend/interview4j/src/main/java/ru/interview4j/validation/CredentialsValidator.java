package ru.interview4j.validation;
/*
 * Date: 30.07.2021
 * Time: 10:40 AM
 * */

import lombok.NonNull;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.interview4j.web.router.request.AuthRequest;

public record CredentialsValidator() implements Validator {

    @Override
    public boolean supports(@NonNull Class<?> aClass) {
        return AuthRequest.class.equals(aClass);
    }

    @Override
    public void validate(@NonNull Object o, @NonNull Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "username", "username.empty");
        ValidationUtils.rejectIfEmpty(errors, "password", "password.empty");

        AuthRequest auth = (AuthRequest) o;
        if (auth.username().trim().length() < 4) {
            errors.rejectValue("username", "min.length");
        } else if (auth.password().trim().length() < 9) {
            errors.rejectValue("password", "min.length");
        }
    }

}
