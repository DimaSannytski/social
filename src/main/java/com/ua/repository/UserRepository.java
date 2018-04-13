package com.ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ua.entity.Person;

@Repository
public interface UserRepository extends JpaRepository<Person, Integer>, JpaSpecificationExecutor<Person>{
	
	@Query("SELECT u FROM Person u WHERE u.email = :email")
	Person findUserByEmail(@Param("email") String email);
	@Query("SELECT u FROM Person u WHERE u.id = :id")
	Person findUserById(@Param("id") int id);
//	@Query("SELECT u FROM Person u WHERE u.firstName = :firstName")
//	Person findUserByfirstName(@Param("firstName") String firstName);
	@Query("SELECT u FROM Person u WHERE u.lastName = :lastName")
	Person findUserBylastName(@Param("lastName") String lastName);



}
