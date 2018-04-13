package com.ua.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class PhotoComent extends BaseEntity{
	private String message;
	@ManyToOne
	private Person person;
	@ManyToOne
	private Photo photo;

}
