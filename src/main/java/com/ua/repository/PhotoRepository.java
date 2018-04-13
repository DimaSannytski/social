package com.ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ua.entity.Person;
import com.ua.entity.Photo;
import com.ua.entity.PhotoAlbum;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer>{
	@Query("SELECT u FROM Photo u WHERE u.person = :person")
	List<Photo> findByPerson(@Param("person") Person person);
	@Query("SELECT u FROM Photo u WHERE u.photoAlbum = :photoAlbum")
	List<Photo> findAllByAlbum(@Param("photoAlbum") PhotoAlbum photoAlbum);


}
