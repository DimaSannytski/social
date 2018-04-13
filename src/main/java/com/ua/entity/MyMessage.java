package com.ua.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class MyMessage extends BaseEntity{
	
	private String message;
	@ManyToOne
	private Person aPerson;
//	@ManyToOne
//	private Person bPerson;
	@ManyToOne
	private Dialog dialog;

	

}
