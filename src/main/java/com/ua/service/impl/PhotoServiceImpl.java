package com.ua.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ua.entity.Person;
import com.ua.entity.Photo;
import com.ua.entity.PhotoAlbum;
import com.ua.repository.PhotoRepository;
import com.ua.service.PhotoService;

@Service
public class PhotoServiceImpl implements PhotoService{

	@Autowired
	private PhotoRepository photoRepository;
	@Override
	public void save(Photo photo) {
		photoRepository.save(photo);
		
	}

	@Override
	public Photo findOne(int id) {
		return photoRepository.getOne(id);
		
	}

	@Override
	public List<Photo> findAll() {
		
		return photoRepository.findAll();
	}

	@Override
	public List<Photo> findAllByPerson(Person person) {
		
		return photoRepository.findByPerson(person);
	}

	@Override
	public List<Photo> findAllByAlbum(PhotoAlbum photoAlbum) {
		
		return photoRepository.findAllByAlbum(photoAlbum);
	}

	@Override
	public List<Photo> findAllByPersonNullAlbum(Person person) {
		List<Photo> photos = photoRepository.findByPerson(person);
		List<Photo> photo = new ArrayList<Photo>();
		for(int i=0;i<photos.size();i++) {
			if(photos.get(i).getPhotoAlbum()==null) photo.add(photos.get(i));
		}
		return photo;
	}

	@Override
	public void delete(int id) {

		photoRepository.deleteById(id);
	}

}
