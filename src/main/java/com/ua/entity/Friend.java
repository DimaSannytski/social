package com.ua.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import com.ua.entity.enums.FriendCategory;
import com.ua.entity.enums.FriendStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @NoArgsConstructor
public class Friend extends BaseEntity{
	@Enumerated(EnumType.STRING)
	private FriendCategory category;
	@Enumerated(EnumType.STRING)
	private FriendStatus friendStatus;
	@ManyToOne
	private Person afriend;
	@ManyToOne 
	private Person bfriend;

}
