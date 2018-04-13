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

@Entity
@Getter @Setter @NoArgsConstructor
public class Dialog extends BaseEntity{
	@OneToMany(mappedBy= "dialog",cascade = CascadeType.ALL)
	List<MyMessage> myMessage = new ArrayList<MyMessage>();
	@ManyToOne
	private Person aPerson;
	@ManyToOne 
	private Person bPerson;

}
