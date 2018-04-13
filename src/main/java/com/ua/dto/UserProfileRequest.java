package com.ua.dto;

import java.util.ArrayList;
import java.util.List;

import com.ua.entity.Wall;
import com.ua.entity.enums.Country;
import com.ua.entity.enums.Male;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter 
@NoArgsConstructor
public class UserProfileRequest {
	private int id;
	private String firstName;
	private String lastName;
	private int age;
	private String email;
	private String about;
	private Country country;
	private Male male;
	private List<Wall>wall = new ArrayList<Wall>();
	private List<Wall>wallMessags = new ArrayList<Wall>();
	
	
	

}
