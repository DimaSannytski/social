package com.ua.mapper;

import com.ua.dto.PhotoComentRequest;
import com.ua.entity.PhotoComent;

public interface PhotoComentMapper {
	
	public static PhotoComent addPhotoComentRequest(PhotoComentRequest request) {
		PhotoComent photoComent= new PhotoComent();
		photoComent.setMessage(request.getMessage());
		photoComent.setPerson(request.getPerson());
		photoComent.setPhoto(request.getPhoto());
		return photoComent;
	}
//	public static PhotoComentRequest comentToRequest(PhotoComent photoComent) {
//		PhotoComentRequest request = new PhotoComentRequest();
//		request.
//		return request;
//	}

}
