package com.ua.service;

import java.util.List;

import com.ua.entity.Wall;
import com.ua.entity.WallComents;

public interface WallComentsService {
	void save(WallComents wallComents);
	WallComents finById(int id);
	List<WallComents> findByWall(Wall wall);
	void delte(int id);

}
