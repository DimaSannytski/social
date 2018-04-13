package com.ua.service;

import java.util.List;

import com.ua.entity.Friend;
import com.ua.entity.Person;

public interface FriendService {
	void save(Friend friend);
	void delete(Friend friend);
	Friend findOne(int id);
	List<Friend> findAll();
	List<Friend> findByBfriend(Person bfriend);
	List<Friend> findByAfriend(Person afriend);

}
