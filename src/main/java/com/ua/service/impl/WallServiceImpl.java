package com.ua.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ua.entity.Person;
import com.ua.entity.Spilnoty;
import com.ua.entity.Wall;
import com.ua.repository.WallRepository;
import com.ua.service.WallService;

@Service
public class WallServiceImpl implements WallService{
	@Autowired
	private WallRepository wallRepository;

	@Override
	public void saveWall(Wall wall) {
		System.out.println(wall);
		
		wallRepository.save(wall);
	}

	@Override
	public Wall finById(int id) {
		
		return wallRepository.getOne(id);
	}

	@Override
	public List<Wall> findAll() {
		
		return wallRepository.findAll();
	}

	@Override
	public List<Wall> findByPerson(Person person) {
		
		return wallRepository.findByPerson(person);
	}

	@Override
	public List<Wall> findBySpilnoty(Spilnoty spilnoty) {
		return wallRepository.findBySpilnoty(spilnoty);
	}

	@Override
	public void delete(int id) {
		wallRepository.deleteById(id);
		
	}

}
