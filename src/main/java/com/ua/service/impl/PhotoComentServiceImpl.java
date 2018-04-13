package com.ua.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ua.entity.Person;
import com.ua.entity.PhotoComent;
import com.ua.repository.PhotoComentRepository;
import com.ua.service.PhotoComentService;

@Service
public class PhotoComentServiceImpl implements PhotoComentService{

	@Autowired
	private PhotoComentRepository photoComentRepository;
	@Override
	public void save(PhotoComent photoComent) {
		photoComentRepository.save(photoComent);
		
	}

	@Override
	public PhotoComent findOne(int id) {
		return photoComentRepository.getOne(id);
	}

	@Override
	public List<PhotoComent> findAll() {
		return photoComentRepository.findAll();
	}

	@Override
	public List<PhotoComent> findAllByPerson(Person person) {
		return photoComentRepository.findAllByPerson(person);
	}

}
