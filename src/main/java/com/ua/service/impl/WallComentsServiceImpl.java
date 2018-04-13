package com.ua.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ua.entity.Wall;
import com.ua.entity.WallComents;
import com.ua.repository.WallComentsRepository;
import com.ua.service.WallComentsService;
@Service
public class WallComentsServiceImpl implements WallComentsService{

	@Autowired
	private WallComentsRepository wallComentsRepository;

	@Override
	public void save(WallComents wallComents) {
		wallComentsRepository.save(wallComents);
		
	}

	@Override
	public WallComents finById(int id) {
		
		return wallComentsRepository.getOne(id);
	}

	@Override
	public List<WallComents> findByWall(Wall wall) {
		
		return wallComentsRepository.findByWall(wall);
	}

	@Override
	public void delte(int id) {

		wallComentsRepository.deleteById(id);
	}
	

}
