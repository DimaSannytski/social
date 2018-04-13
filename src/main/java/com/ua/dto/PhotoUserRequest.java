package com.ua.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class PhotoUserRequest {
	private MultipartFile  file;
	private int id;

}
