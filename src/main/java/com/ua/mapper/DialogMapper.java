package com.ua.mapper;

import com.ua.dto.DialogRequest;
import com.ua.entity.Dialog;

public interface DialogMapper {

	public static Dialog createDialogRequest(DialogRequest request) {
		Dialog dialog = new Dialog();
		dialog.setAPerson(request.getAPerson());
		dialog.setBPerson(request.getBPerson());
		dialog.setMyMessage(dialog.getMyMessage());
		return dialog;
	}
}
