package com.russell.test.redis.web.validator;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.russell.test.redis.app.User;
import com.russell.test.redis.service.TService;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    
    @Resource
    TService service;
    
    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        User user = service.findByEmail(value);
        if (user == null) {
            return true;
        }
        return value.equalsIgnoreCase(user.getEmail());
    }

}
