package com.ua.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.ua.entity.enums.Male;
import com.ua.entity.enums.Role;
import com.ua.validator.CheckPasswordsMatch;
import com.ua.validator.UniquePersonMail;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter @Setter @NoArgsConstructor
@CheckPasswordsMatch
public class RegisterRequest {

	private int id;
	@NotNull(message = "this field cant be empty")
	@NotBlank
	@UniquePersonMail(message="Mail already exist")
	private String email;
	@NotNull(message = "this field cant be empty")
	
	private String firstName;
	@NotNull(message = "this field cant be empty")
	
	private String lastName;
	@NotNull(message = "this field cant be empty")
	@NotBlank
	private String password;
	@Min(value = 10, message = "Age should not be less than 10")
  @Max(value = 150, message = "Age should not be greater than 150")
	private int age;
	private String passwordConfirmation;
	private Role role;
	private Male male;
}
