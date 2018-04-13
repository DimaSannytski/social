package com.ua.mapper;
import com.ua.dto.MyMessageRequest;
import com.ua.entity.MyMessage;

public interface MyMessageMapper {

	public static MyMessage messageCreateRequest(MyMessageRequest request) {
		MyMessage myMessage = new MyMessage();
		myMessage.setAPerson(request.getAPerson());
		myMessage.setDialog(request.getDialog());
		myMessage.setMessage(request.getMessage());
		return myMessage;
		
	}

}
