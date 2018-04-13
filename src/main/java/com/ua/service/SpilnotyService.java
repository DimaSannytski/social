package com.ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ua.dto.GroupNameFilter;
import com.ua.entity.Person;
import com.ua.entity.Spilnoty;

public interface SpilnotyService {
	
	void save(Spilnoty spilnoty);
	void delete(Spilnoty spilnoty);
	Spilnoty findOne(int id);
	List<Spilnoty> findAll();
	List<Spilnoty> findAllByAdmin(Person person);
	Page<Spilnoty> findGroupByName(Pageable pageable, GroupNameFilter filter);

}
