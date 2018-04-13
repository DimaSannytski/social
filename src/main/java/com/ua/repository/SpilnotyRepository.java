package com.ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ua.entity.Person;
import com.ua.entity.Spilnoty;

@Repository
public interface SpilnotyRepository extends JpaRepository<Spilnoty, Integer>,JpaSpecificationExecutor<Spilnoty>{

	@Query("SELECT u FROM Spilnoty u WHERE u.admin = :admin")
	List<Spilnoty> findByPersonAdmin(@Param("admin") Person admin);
}
