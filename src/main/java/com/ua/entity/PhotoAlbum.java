package com.ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter 
@Setter @NoArgsConstructor
public class PhotoAlbum extends BaseEntity{
	private String name;
	private boolean isMainAlbum;
	@OneToMany (mappedBy="photoAlbum",cascade = CascadeType.ALL)
	List<Photo> photo = new ArrayList<Photo>();
	@ManyToOne
	private Person person;
	

}
