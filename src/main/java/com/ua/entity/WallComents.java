package com.ua.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class WallComents extends BaseEntity{
	private String postComent;
	@ManyToOne
	private Wall wall;
	@ManyToOne
	private Person person;

}
