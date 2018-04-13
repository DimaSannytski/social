package com.ua.service;

import java.util.List;

import com.ua.entity.Dialog;
import com.ua.entity.MyMessage;

public interface MyMessageService {

	void save(MyMessage myMessage);
	void delete(MyMessage myMessage);
	MyMessage findOne(int id);
	List<MyMessage> findByDialog(Dialog dialog);
}
