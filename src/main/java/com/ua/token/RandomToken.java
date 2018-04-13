package com.ua.token;

import org.apache.commons.lang.RandomStringUtils;

public final class RandomToken {
	
	public static String generedeRandomString() {
		String string = RandomStringUtils.randomAlphabetic(80);
		System.out.println("Rand.token:"+string);
		return string;
	}

}
