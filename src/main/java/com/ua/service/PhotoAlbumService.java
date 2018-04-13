package com.ua.service;

import java.util.List;

import com.ua.entity.Person;
import com.ua.entity.PhotoAlbum;

public interface PhotoAlbumService {
	void save(PhotoAlbum album);
	PhotoAlbum findOne(int id);
	List<PhotoAlbum> findAll();
	List<PhotoAlbum> findAllByUser(Person person);
	void delete(int id);

}
