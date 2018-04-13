package com.ua.dto;

import com.ua.validator.CheckPassMatch;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @CheckPassMatch
public class ChangePassDto {
	
	private String password;
	private String passwordConfirmation;

}
