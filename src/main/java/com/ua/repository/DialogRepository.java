package com.ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ua.entity.Dialog;
import com.ua.entity.Person;

@Repository
public interface DialogRepository extends JpaRepository<Dialog, Integer>{
	@Query("SELECT u FROM Dialog u WHERE u.aPerson = :aPerson")
	List<Dialog> findByPerson(@Param("aPerson") Person person);

}
