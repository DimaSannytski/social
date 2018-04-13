package com.ua.dto;

import org.springframework.web.multipart.MultipartFile;

import com.ua.entity.enums.Country;
import com.ua.entity.enums.Male;
import com.ua.entity.enums.Role;
import com.ua.validator.UniquePersonMail;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class EditUserRequest {

	private int id;
	@UniquePersonMail(message="Mail already exist")
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	private int age;
	
	private String phoneNumber;
	private MultipartFile file;
	private Role role;
	
	private Male male;
	
	private Country country;
	
	private String about;
	
	
	

}
