package com.ua.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.ua.service.UserService;

public class UniquePersonMailValidation implements ConstraintValidator<UniquePersonMail, String>{

	@Autowired
	private UserService personService;
	@Override
	public void initialize(UniquePersonMail constraintAnnotation) {
		
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value==null) {
			return false;
		}
		if(personService.finByUserEmail(value)!=null) {
			return false;
		}else {
			return true;
		}
		
	}
	}


