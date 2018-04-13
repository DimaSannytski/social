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
import org.springframework.stereotype.Service;

import com.ua.dto.GroupNameFilter;
import com.ua.entity.Person;
import com.ua.entity.Spilnoty;
import com.ua.repository.SpilnotyRepository;
import com.ua.service.SpilnotyService;

@Service
public class SpilnotyServiceImpl implements SpilnotyService{

	@Autowired
	private SpilnotyRepository spilnotyRepository;
	@Override
	public void save(Spilnoty spilnoty) {

		 spilnotyRepository.save(spilnoty);
		
	}

	@Override
	public void delete(Spilnoty spilnoty) {

		spilnotyRepository.delete(spilnoty);
	}

	@Override
	public Spilnoty findOne(int id) {
		return spilnotyRepository.getOne(id);
	}

	@Override
	public List<Spilnoty> findAll() {
		return spilnotyRepository.findAll();
	}

	@Override
	public List<Spilnoty> findAllByAdmin(Person person) {
		return spilnotyRepository.findByPersonAdmin(person);
	}

	@Override
	public Page<Spilnoty> findGroupByName(Pageable pageable, GroupNameFilter filter) {
		return spilnotyRepository.findAll(getSpecification(filter),pageable);
	}
	private Specification<Spilnoty> getSpecification(GroupNameFilter filter){
		return new Specification<Spilnoty>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Spilnoty> arg0, CriteriaQuery<?> arg1, CriteriaBuilder arg2) {
				return arg2.like(arg0.get("groupName"), "%"+filter.getSearch()+"%");
			}
		};
	}

}
