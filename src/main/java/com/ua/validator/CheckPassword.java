package com.ua.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.ua.dto.ChangePassDto;

@Component
public class CheckPassword implements ConstraintValidator<CheckPassMatch, ChangePassDto> {

	@Override
	public void initialize(CheckPassMatch constraintAnnotation) {

	}

	@Override
	public boolean isValid(ChangePassDto user, ConstraintValidatorContext context) {
		
		if(user.getPassword() == null || user.getPasswordConfirmation() == null) {
			return false;
		}
		
		if(user.getPassword().equals(user.getPasswordConfirmation())) {
			return true;
		}
		
		
		return false;
	}


}