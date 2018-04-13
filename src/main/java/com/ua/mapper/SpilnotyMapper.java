package com.ua.mapper;

import com.ua.dto.SpilnotyRequest;
import com.ua.entity.Spilnoty;

public interface SpilnotyMapper {
	public static Spilnoty createSpilnotyRequest(SpilnotyRequest spilnotyRequest) {
		Spilnoty spilnoty = new Spilnoty();
		spilnoty.setGroupName(spilnotyRequest.getGroupName());
		spilnoty.setAbout(spilnotyRequest.getAbout());
		spilnoty.setAdmin(spilnotyRequest.getAdmin());
		spilnoty.setGroupName(spilnotyRequest.getGroupName());
		spilnoty.setPersons(spilnotyRequest.getPersons());
		spilnoty.setWalls(spilnotyRequest.getWalls());
		spilnoty.setCategory(spilnotyRequest.getCategory());
		
		return spilnoty;
	}
	public static SpilnotyRequest spilnotyToRequest(Spilnoty spilnoty) {
		SpilnotyRequest spilnotyRequest = new SpilnotyRequest();
		spilnotyRequest.setId(spilnoty.getId());
		spilnotyRequest.setGroupName(spilnoty.getGroupName());
		spilnotyRequest.setAbout(spilnoty.getAbout());
		spilnotyRequest.setAdmin(spilnoty.getAdmin());
		spilnotyRequest.setPersons(spilnoty.getPersons());
		spilnotyRequest.setWalls(spilnoty.getWalls());
		spilnotyRequest.setCategory(spilnoty.getCategory());
		return spilnotyRequest;
	}
	public static Spilnoty editSpilnotyRequest(SpilnotyRequest spilnotyRequest) {
		Spilnoty spilnoty = new Spilnoty();
		spilnoty.setId(spilnotyRequest.getId());
		spilnoty.setGroupName(spilnotyRequest.getGroupName());
		spilnoty.setAbout(spilnotyRequest.getAbout());
		spilnoty.setAdmin(spilnotyRequest.getAdmin());
		spilnoty.setGroupName(spilnotyRequest.getGroupName());
		spilnoty.setPersons(spilnotyRequest.getPersons());
		spilnoty.setWalls(spilnotyRequest.getWalls());
		spilnoty.setCategory(spilnotyRequest.getCategory());
		
		return spilnoty;
	}
}
