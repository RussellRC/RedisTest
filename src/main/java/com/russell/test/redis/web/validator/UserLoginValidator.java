package com.russell.test.redis.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.russell.test.redis.web.form.Login;


public class UserLoginValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Login.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        final Login login = (Login) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required");
    }

}
