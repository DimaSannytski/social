package com.ua.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.ua.entity.Dialog;
import com.ua.entity.Person;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class MyMessageRequest {

	@NotNull(message = "this field cant be empty")
	@NotBlank
	private String message;
	private Dialog dialog;
	private Person aPerson;

}
