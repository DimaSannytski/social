package com.ua.controller;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ua.dto.GroupNameFilter;
import com.ua.dto.PersonNameFilter;
import com.ua.entity.Person;
import com.ua.entity.Spilnoty;
import com.ua.service.SpilnotyService;
import com.ua.service.UserService;
import com.ua.service.utils.CustomFileUtils;

@Controller
	
	public class SearchController {
	@Autowired UserService userService;
	@Autowired SpilnotyService spilnotyService;
	
	
	@PostMapping("/search")
	public String searchUserByName(@PageableDefault Pageable pageable,Model model,@RequestParam("search") String search,Principal principal) throws IOException {
		Page<Person> page =  userService.findPersonByFirstName(pageable,new PersonNameFilter(search));
		for (Person person : page) {
			
					person.setPhoto(
							CustomFileUtils.getImage("user_" + person.getId(), person.getPhoto())
							
					);
		}
		model.addAttribute("usersModel",page.getContent());
		return "user/all";
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/search/admin")
	public String searchUserByNameAdmin(@PageableDefault Pageable pageable,Model model,@RequestParam("search") String search,Principal principal) throws IOException {
		Page<Person> page =  userService.findPersonByFirstName(pageable,new PersonNameFilter(search));
		for (Person person : page) {
			
					person.setPhoto(
							CustomFileUtils.getImage("user_" + person.getId(), person.getPhoto())
							
					);
		}
		model.addAttribute("usersModel",page.getContent());
		return "user/all";
	}
	@PostMapping("/search/group")
	public String searchGroupByName(@PageableDefault Pageable pageable,Model model,@RequestParam("search") String search,Principal principal) {
		Page<Spilnoty> page =  spilnotyService.findGroupByName(pageable,new GroupNameFilter(search));
		model.addAttribute("spilnotyAll",page.getContent());
		return "/group/allgroup";
	}

}
