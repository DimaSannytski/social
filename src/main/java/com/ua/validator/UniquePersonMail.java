package com.ua.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
@Target(value= {ElementType.FIELD, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint (validatedBy=UniquePersonMailValidation.class)
public @interface UniquePersonMail {
	String message() default "Mail alredy exist";
	Class<?>[] groups() default{};
	Class<? extends Payload>[] payload() default {};

}
