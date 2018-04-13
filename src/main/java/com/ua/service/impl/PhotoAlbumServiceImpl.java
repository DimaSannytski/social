package com.ua.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ua.entity.Person;
import com.ua.entity.PhotoAlbum;
import com.ua.repository.PhotoAlbumRepository;
import com.ua.service.PhotoAlbumService;

@Service
public class PhotoAlbumServiceImpl implements PhotoAlbumService{

	@Autowired
	private PhotoAlbumRepository photoAlbumRepository;
	@Override
	public void save(PhotoAlbum album) {
		photoAlbumRepository.save(album);
		
	}

	@Override
	public PhotoAlbum findOne(int id) {
		
		return photoAlbumRepository.getOne(id);
	}

	@Override
	public List<PhotoAlbum> findAll() {
		
		return photoAlbumRepository.findAll();
	}

	@Override
	public List<PhotoAlbum> findAllByUser(Person person) {
		
		return photoAlbumRepository.findByPerson(person);
	}

	@Override
	public void delete(int id) {

		photoAlbumRepository.deleteById(id);
	}

}
