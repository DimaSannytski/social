package com.ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "photos")
public class Photo extends BaseEntity{

	private String file;
	private String name;
	@ManyToOne
	private Person person;
	@ManyToOne
	private Spilnoty spilnoty;
	@ManyToMany(mappedBy="photoLikes",cascade = CascadeType.ALL)
	List<Person> persons = new ArrayList<Person>();
	@ManyToOne
	private PhotoAlbum photoAlbum;
	@OneToMany(mappedBy="photo",cascade = CascadeType.ALL)
	List<PhotoComent> photoComent = new ArrayList<PhotoComent>();
}
