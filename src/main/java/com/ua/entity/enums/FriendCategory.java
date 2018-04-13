package com.ua.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public enum FriendCategory {
	BEST("Найкращі"), SCHOOL("Друзі по школі"), WORK("Друзі по роботі"), UNIVEERSITY("Друзі по ВНЗ");
	private String category;

}
