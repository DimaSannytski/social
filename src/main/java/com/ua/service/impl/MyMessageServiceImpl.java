package com.ua.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ua.entity.Dialog;
import com.ua.entity.MyMessage;
import com.ua.repository.MyMessageRepository;
import com.ua.service.MyMessageService;

@Service
public class MyMessageServiceImpl implements MyMessageService{

	@Autowired
	private MyMessageRepository myMessageRepository;
	@Override
	public void save(MyMessage myMessage) {

		myMessageRepository.save(myMessage);
	}

	@Override
	public void delete(MyMessage myMessage) {

		myMessageRepository.delete(myMessage);
	}

	@Override
	public MyMessage findOne(int id) {
		return myMessageRepository.getOne(id);
	}

	@Override
	public List<MyMessage> findByDialog(Dialog dialog) {
		return myMessageRepository.findByDialog(dialog);
	}

}
