package com.russell.test.redis.web.validator;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = {UniqueEmailValidator.class})
@Documented
public @interface UniqueEmail {

    String message() default "Mail already Exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
}
