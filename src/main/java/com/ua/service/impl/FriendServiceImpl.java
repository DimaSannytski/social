package com.ua.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ua.entity.Friend;
import com.ua.entity.Person;
import com.ua.repository.FriendRepository;
import com.ua.service.FriendService;

@Service
public class FriendServiceImpl implements FriendService{

	@Autowired FriendRepository friendRepository;
	@Override
	public void save(Friend friend) {

		friendRepository.save(friend);
	}

	@Override
	public void delete(Friend friend) {

		friendRepository.delete(friend);
	}

	@Override
	public Friend findOne(int id) {
		return friendRepository.getOne(id);
	}

	@Override
	public List<Friend> findAll() {
		return friendRepository.findAll();
	}

	@Override
	public List<Friend> findByBfriend(Person bfriend) {
		return friendRepository.findByBfriend(bfriend);
	}

	@Override
	public List<Friend> findByAfriend(Person afriend) {
		return friendRepository.findByAfriend(afriend);
	}

}
