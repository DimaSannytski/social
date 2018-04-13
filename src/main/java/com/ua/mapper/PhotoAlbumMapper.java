package com.ua.mapper;

import com.ua.dto.PhotoAlbumRequest;
import com.ua.entity.PhotoAlbum;

public interface PhotoAlbumMapper {
	public static PhotoAlbum createAlbum(PhotoAlbumRequest request) {
		PhotoAlbum photoAlbum = new PhotoAlbum();
		photoAlbum.setName(request.getName());
		photoAlbum.setPerson(request.getPerson());
		photoAlbum.setPhoto(request.getPhoto());
		photoAlbum.setMainAlbum(false);
		return photoAlbum;
	}

}
