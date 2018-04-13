package com.ua.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.ua.entity.Person;
import com.ua.entity.Photo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class PhotoComentRequest {
	private int id;
	private Person person;
	private Photo photo;
	@NotNull(message = "this field cant be empty")
	@NotBlank
	private String message;

}
