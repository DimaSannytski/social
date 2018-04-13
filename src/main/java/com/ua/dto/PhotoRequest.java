package com.ua.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ua.entity.Person;
import com.ua.entity.PhotoAlbum;
import com.ua.entity.PhotoComent;
import com.ua.entity.Spilnoty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class PhotoRequest {
	private int id;
	private String name;
	private MultipartFile file;
	private PhotoAlbum photoAlbum;
	private Person person;
	private List<PhotoComent> photoComent;
	private Spilnoty spilnoty;
	private List<Person> persons;
}
