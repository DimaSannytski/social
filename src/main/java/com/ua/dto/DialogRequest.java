package com.ua.dto;

import java.util.ArrayList;
import java.util.List;

import com.ua.entity.MyMessage;
import com.ua.entity.Person;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class DialogRequest {
	List<MyMessage> myMessage = new ArrayList<MyMessage>();
	private Person aPerson;
	private Person bPerson;

}
