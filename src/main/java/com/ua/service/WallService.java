package com.ua.service;

import java.util.List;

import com.ua.entity.Person;
import com.ua.entity.Spilnoty;
import com.ua.entity.Wall;

public interface WallService {
	void saveWall(Wall wall);
	Wall finById(int id);
	List<Wall> findAll();
	List<Wall> findByPerson(Person person);
	List<Wall> findBySpilnoty(Spilnoty spilnoty);
	void delete(int id);

}
