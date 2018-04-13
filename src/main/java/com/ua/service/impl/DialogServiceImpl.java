package com.ua.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ua.entity.Dialog;
import com.ua.entity.Person;
import com.ua.repository.DialogRepository;
import com.ua.service.DialogService;

@Service
public class DialogServiceImpl implements DialogService{

	@Autowired
	private DialogRepository dialogRepository;
	@Override
	public void save(Dialog dialog) {
		dialogRepository.save(dialog);
		
	}

	@Override
	public Dialog findOne(int id) {
		return dialogRepository.getOne(id);
	}

	@Override
	public List<Dialog> findByAPerson(Person person) {
		return dialogRepository.findByPerson(person);
	}

}
