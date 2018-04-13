package com.ua.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public enum GroupCategory {
	SPORT("Спорт"),MUSIC("Музика"),FILMS("Фільми"),TRIP("Туризм"),GAMES("Ігри"),ENTERT("Розваги"),ENOTHER("Інше");

	private String category;
}
