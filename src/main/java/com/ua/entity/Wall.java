package com.ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "wall")
public class Wall extends BaseEntity{

	private String message;
	@ManyToOne
	private Person fromPerson;
	@ManyToOne
	private Spilnoty spilnoty;
	@ManyToOne
	private Person person;
	@OneToMany(mappedBy="wall",cascade = CascadeType.ALL)
	List<WallComents> wallComents = new ArrayList<WallComents>();

	

	

}
