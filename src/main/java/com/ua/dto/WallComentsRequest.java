package com.ua.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.ua.entity.Person;
import com.ua.entity.Wall;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class WallComentsRequest {
	@NotNull(message = "this field cant be empty")
	@NotBlank
	private String post;
	private Wall wall;
	private Person person;

}
