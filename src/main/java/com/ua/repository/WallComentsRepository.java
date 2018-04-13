package com.ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ua.entity.Wall;
import com.ua.entity.WallComents;

@Repository
public interface WallComentsRepository extends JpaRepository<WallComents, Integer>{
	@Query("SELECT u FROM WallComents u WHERE u.wall = :wall")
	List<WallComents> findByWall(@Param("wall") Wall wall);

}
