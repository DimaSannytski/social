package com.ua.mapper;

import com.ua.dto.WallRequest;
import com.ua.entity.Wall;

public interface WallMapper {
	

	
	public static Wall sendWallPost(WallRequest request) {
		Wall wall = new Wall();
		wall.setMessage(request.getMessage());
		wall.setPerson(request.getPerson());
		wall.setFromPerson(request.getFromPerson());
		wall.setSpilnoty(request.getSpilnoty());
		return wall;
	}
	public static WallRequest wallRequestToWall(Wall wall) {
		WallRequest request = new WallRequest();
		request.setId(wall.getId());
		request.setMessage(wall.getMessage());
		request.setFromPerson(wall.getFromPerson());
		request.setPerson(wall.getPerson());
		request.setWallComents(wall.getWallComents());
		return request;
	}

}
