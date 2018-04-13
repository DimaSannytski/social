package com.ua.service;

import java.util.List;

import com.ua.entity.Person;
import com.ua.entity.PhotoComent;

public interface PhotoComentService {
	void save(PhotoComent photoComent);
	PhotoComent findOne(int id);
	List<PhotoComent> findAll();
	List<PhotoComent> findAllByPerson(Person person);

}
