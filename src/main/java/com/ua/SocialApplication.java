package com.ua;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ua.entity.Person;
import com.ua.entity.enums.Role;
import com.ua.repository.UserRepository;

@SpringBootApplication
public class SocialApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SocialApplication.class, args);
		addAdmin(context);
	}
	static void addAdmin(ConfigurableApplicationContext context) {
		String adminEmail = "admin@gmail.com";
		String adminPassword = "123";
		String name = "admin";
		String lastName = "adminovich";
		
		UserRepository userRepository = context.getBean(UserRepository.class);
		Person entity = userRepository.findUserByEmail(adminEmail);
		if(entity == null) {
			PasswordEncoder encoder = context.getBean(PasswordEncoder.class);
			
			entity = new Person();
			entity.setEmail(adminEmail);
			entity.setPassword(encoder.encode(adminPassword));
			entity.setRole(Role.ROLE_ADMIN);
			entity.setFirstName(name);
			entity.setLastName(lastName);
			entity.setActivated(true);
			
			userRepository.save(entity);
		}
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SocialApplication.class);
	}
	
	
}
