package com.ua.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.ua.entity.Person;
import com.ua.entity.Spilnoty;
import com.ua.entity.WallComents;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter @Setter @NoArgsConstructor
public class WallRequest {
	private int id;
	@NotNull(message = "this field cant be empty")
	@NotBlank
	private String message;
	List<WallComents> wallComents = new ArrayList<WallComents>();
	private Person person;
	private Person fromPerson;
	private Spilnoty spilnoty;

}
