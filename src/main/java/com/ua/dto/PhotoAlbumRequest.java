package com.ua.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.ua.entity.Person;
import com.ua.entity.Photo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class PhotoAlbumRequest {

	private int id;
	@NotNull(message = "this field cant be empty")
	@NotBlank
	private String name;
	private Person person;
	private List<Photo> photo;
}
