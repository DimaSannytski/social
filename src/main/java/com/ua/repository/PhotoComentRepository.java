package com.ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ua.entity.Person;
import com.ua.entity.PhotoComent;

@Repository
public interface PhotoComentRepository extends JpaRepository<PhotoComent, Integer>{
	@Query("SELECT u FROM PhotoComent u WHERE u.person = :person")
	List<PhotoComent> findAllByPerson(@Param("person") Person person);

}
