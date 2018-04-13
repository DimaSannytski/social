package com.ua.service;

import java.util.List;

import com.ua.entity.Person;
import com.ua.entity.Photo;
import com.ua.entity.PhotoAlbum;

public interface PhotoService {
	void save(Photo photo);
	Photo findOne(int id);
	List<Photo> findAll();
	List<Photo> findAllByPerson(Person person);
	List<Photo> findAllByPersonNullAlbum(Person person);
	List<Photo> findAllByAlbum(PhotoAlbum photoAlbum);
	void delete(int id);

}
