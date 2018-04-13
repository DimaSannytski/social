package com.ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ua.entity.Person;
import com.ua.entity.PhotoAlbum;

@Repository
public interface PhotoAlbumRepository extends JpaRepository<PhotoAlbum, Integer>{
	@Query("SELECT u FROM PhotoAlbum u WHERE u.person = :person")
	List<PhotoAlbum> findByPerson(@Param("person") Person person);

}
