package com.ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ua.entity.enums.GroupCategory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Getter @Setter @NoArgsConstructor
@Table(name = "spilnoty", indexes =  @Index(columnList = "groupName"))
public class Spilnoty extends BaseEntity{
	private String groupName;
	private String about;
	private String photo;
	@Enumerated(EnumType.STRING)
	private GroupCategory category;
	@ManyToMany(mappedBy="spilnotys",cascade = CascadeType.ALL)
	List<Person> persons = new ArrayList<Person>();
	@ManyToOne
	private Person admin;
	@OneToMany(mappedBy="spilnoty",cascade = CascadeType.ALL)
	List<Wall>walls = new ArrayList<Wall>();
	@OneToMany(mappedBy="spilnoty",cascade = CascadeType.ALL)
	List<Photo> photos = new ArrayList<Photo>();

}
