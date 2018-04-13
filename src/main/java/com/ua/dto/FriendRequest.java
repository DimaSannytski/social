package com.ua.dto;

import com.ua.entity.Person;
import com.ua.entity.enums.FriendCategory;
import com.ua.entity.enums.FriendStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class FriendRequest {
	private int id;
	private FriendCategory category;
	private FriendStatus status;
	private Person bfriend;
	private Person afriend;

}
