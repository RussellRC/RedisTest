package com.russell.test.redis.web.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.russell.test.redis.web.UserSignUp;


public class UserSignUpValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserSignUp.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        final UserSignUp userSignUp = (UserSignUp) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password2", "required");
        if (!StringUtils.equals(userSignUp.getPassword(), userSignUp.getPassword2())) {
            errors.rejectValue("password2", "error.match");
        }
    }

}
