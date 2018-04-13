package com.ua.mapper;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.ua.dto.ChangePassDto;
import com.ua.dto.EditUserRequest;
import com.ua.dto.RegisterRequest;
import com.ua.dto.UserProfileRequest;
import com.ua.entity.Person;
import com.ua.entity.enums.Role;

public interface UserMapper {
	public static User toSecurityUser(Person entity) {
		return new User (entity.getEmail(), entity.getPassword(), AuthorityUtils.createAuthorityList(String.valueOf(entity.getRole())));
	}
	public static Person userRegister(RegisterRequest request) {
		Person user = new Person();
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmail(request.getEmail().toLowerCase());
		user.setPassword(request.getPassword());
		user.setMale(request.getMale());
		user.setAge(request.getAge());
		user.setRole(Role.ROLE_USER);
		user.setPhoto("r");
		return user;
	}
	public static Person changePass(ChangePassDto request) {
		Person person = new Person();
		person.setPassword(request.getPassword());
		return person;
	}

	public static UserProfileRequest entityToUserProfile(Person person) {
		UserProfileRequest userProfileRequest = new UserProfileRequest();
		userProfileRequest.setId(person.getId());
		userProfileRequest.setAbout(person.getAbout());
		userProfileRequest.setAge(person.getAge());
		userProfileRequest.setEmail(person.getEmail());
		userProfileRequest.setLastName(person.getLastName());
		userProfileRequest.setFirstName(person.getFirstName());
		userProfileRequest.setCountry(person.getCountry());
		userProfileRequest.setMale(person.getMale());
		userProfileRequest.setWall(person.getWall());
		userProfileRequest.setWallMessags(person.getWallMassage());
		return userProfileRequest;
		
	}
	public static EditUserRequest editUserProfile(Person person) {
		EditUserRequest editUserRequest = new EditUserRequest();
		editUserRequest.setId(person.getId());
		editUserRequest.setAbout(person.getAbout());
		editUserRequest.setAge(person.getAge());
		editUserRequest.setCountry(person.getCountry());
		editUserRequest.setEmail(person.getEmail());
		editUserRequest.setMale(person.getMale());
		editUserRequest.setPassword(person.getPassword());
		editUserRequest.setPhoneNumber(person.getPhoneNumber());
		editUserRequest.setFirstName(person.getFirstName());
		editUserRequest.setLastName(person.getLastName());
		return editUserRequest;
		
	}
	public static Person editRequestEntity(EditUserRequest request) {
		Person person = new Person();
		person.setId(request.getId());
		person.setFirstName(request.getFirstName());
		person.setLastName(request.getLastName());
		person.setAbout(request.getAbout());
		person.setAge(request.getAge());
		person.setCountry(request.getCountry());
		person.setEmail(request.getEmail());
		person.setMale(request.getMale());
		person.setPassword(request.getPassword());
		person.setPhoneNumber(request.getPhoneNumber());
		person.setRole(Role.ROLE_USER);
		return person;
	}

}
