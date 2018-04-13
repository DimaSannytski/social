package com.ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ua.dto.PersonNameFilter;
import com.ua.entity.Person;

public interface UserService {
	
	void saveUser(Person user);
	void editUser(Person user);
	Person findByUserId(int userId);
	Person finByUserEmail(String email);
	void updateUser(Person user);
	List<Person> findAll();
	void delete(int id);
//	Person findByFirstName(String firstName);
//	Person findByLastName(String lastName);
	Page<Person> findPersonByFirstName(Pageable pageable,PersonNameFilter filter);

}
