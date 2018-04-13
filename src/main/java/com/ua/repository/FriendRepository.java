package com.ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ua.entity.Friend;
import com.ua.entity.Person;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer>{

	@Query("SELECT u FROM Friend u WHERE u.bfriend = :bfriend")
	List<Friend> findByBfriend(@Param("bfriend") Person bfriend);
	@Query("SELECT u FROM Friend u WHERE u.afriend = :afriend")
	List<Friend> findByAfriend(@Param("afriend") Person afriend);
}
