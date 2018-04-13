package com.ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ua.entity.Person;
import com.ua.entity.Spilnoty;
import com.ua.entity.Wall;

@Repository
public interface WallRepository extends JpaRepository<Wall, Integer>{

	@Query("SELECT u FROM Wall u WHERE u.person = :person")
	List<Wall> findByPerson(@Param("person") Person person);
	@Query("SELECT u FROM Wall u WHERE u.spilnoty = :spilnoty")
	List<Wall> findBySpilnoty(@Param("spilnoty") Spilnoty spilnoty);
}
