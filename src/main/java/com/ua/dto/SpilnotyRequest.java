package com.ua.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.ua.entity.Person;
import com.ua.entity.Photo;
import com.ua.entity.Wall;
import com.ua.entity.enums.GroupCategory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter @NoArgsConstructor
public class SpilnotyRequest {
	private int id;
	@NotNull(message = "this field cant be empty")
	@NotBlank
	private String groupName;
	private String about;
	private Person admin;
	private MultipartFile file;
	private GroupCategory category;
	List<Person> persons = new ArrayList<Person>();
	List<Wall> walls= new ArrayList<Wall>();
	private List<Photo> photos= new ArrayList<Photo>();

}
