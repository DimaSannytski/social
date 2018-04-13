package com.ua.mapper;

import com.ua.dto.WallComentsRequest;
import com.ua.entity.WallComents;

public interface WallComentsMapper {

	public static WallComents sendWallComentPost(WallComentsRequest request) {
		WallComents wallComents = new WallComents();
		wallComents.setWall(request.getWall());
		wallComents.setPerson(request.getPerson());
		wallComents.setPostComent(request.getPost());
		return wallComents;
	}
}
