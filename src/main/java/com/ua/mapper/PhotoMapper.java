package com.ua.mapper;

import com.ua.dto.PhotoRequest;
import com.ua.entity.Photo;

public interface PhotoMapper {
	public static Photo UploadPhotoRequest(PhotoRequest request) {
		Photo photo = new Photo();
		photo.setId(request.getId());
		photo.setName(request.getName());
		photo.setFile(request.getFile().getOriginalFilename());
		photo.setPhotoAlbum(request.getPhotoAlbum());
		photo.setPerson(request.getPerson());
		photo.setPhotoComent(request.getPhotoComent());
		photo.setSpilnoty(request.getSpilnoty());
		return photo;
	}

}
