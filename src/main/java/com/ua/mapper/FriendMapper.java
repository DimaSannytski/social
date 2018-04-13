package com.ua.mapper;

import com.ua.dto.FriendRequest;
import com.ua.entity.Friend;
import com.ua.entity.enums.FriendStatus;

public interface FriendMapper {
	public static Friend addFriend(FriendRequest friendRequest) {
		Friend friend = new Friend();
		friend.setAfriend(friendRequest.getAfriend());
		friend.setBfriend(friendRequest.getBfriend());
		friend.setCategory(friendRequest.getCategory());
		friend.setFriendStatus(FriendStatus.WAITING);
		return friend;
	}

}
