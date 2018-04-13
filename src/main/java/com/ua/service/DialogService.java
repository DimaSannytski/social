package com.ua.service;

import java.util.List;

import com.ua.entity.Dialog;
import com.ua.entity.Person;

public interface DialogService {
	
	void save(Dialog dialog);
	Dialog findOne(int id);
	List<Dialog> findByAPerson(Person person);

}
