package com.ua.service.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ua.dto.PersonNameFilter;
import com.ua.entity.Person;
import com.ua.repository.UserRepository;
import com.ua.service.UserService;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void saveUser(Person entity) {
		
		String password = entity.getPassword();
		entity.setPassword(passwordEncoder.encode(password));
		userRepository.save(entity);
		
	}

	@Override
	public void editUser(Person user) {
		userRepository.save(user);
		
	}

	@Override
	public Person findByUserId(int id) {
		
		return userRepository.findUserById(id);
	}

	@Override
	public Person finByUserEmail(String email) {
		
		return userRepository.findUserByEmail(email);
	}

	@Override
	public void updateUser(Person user) {
		userRepository.save(user);
		
	}

	@Override
	public List<Person> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void delete(int id) {
		userRepository.deleteById(id);
		
	}

	@Override
	public Page<Person> findPersonByFirstName(Pageable pageable, PersonNameFilter filter) {
		return userRepository.findAll(getSpecification(filter), pageable);
	}
	
	private Specification<Person> getSpecification(PersonNameFilter filter){
		return new Specification<Person>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Person> arg0, CriteriaQuery<?> arg1, CriteriaBuilder arg2) {
				if(filter.getSearch().isEmpty()) return null;
				
				return arg2.like(arg0.get("firstName"), "%"+filter.getSearch()+"%");
			}
		};
	}



}
