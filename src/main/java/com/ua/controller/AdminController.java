package com.ua.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ua.dto.EditUserRequest;
import com.ua.dto.PersonNameFilter;
import com.ua.dto.PhotoRequest;
import com.ua.entity.Person;
import com.ua.entity.Photo;
import com.ua.entity.PhotoAlbum;
import com.ua.entity.enums.Country;
import com.ua.entity.enums.Male;
import com.ua.mapper.PhotoMapper;
import com.ua.mapper.UserMapper;
import com.ua.service.PhotoAlbumService;
import com.ua.service.PhotoService;
import com.ua.service.UserService;
import com.ua.service.utils.CustomFileUtils;

@Controller
@RequestMapping("/admin")
@SessionAttributes({"editModel","editPhotoModel"})
public class AdminController {
	@Autowired
	private UserService userService;
	@Autowired PhotoService photoService;
	@Autowired PhotoAlbumService  photoAlbumService;
	
	
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public String showAdmin(Principal principal,Model model) throws IOException {
		Person person = userService.finByUserEmail(principal.getName());
		if(person==null) return"redirect:/";
		List<Person>persons = userService.findAll();
		for (Person persono : persons) {
			persono.setPhoto(
					CustomFileUtils.getImage("user_" + persono.getId(), persono.getPhoto())
					);
		}
	
		model.addAttribute("usersModel",persons);
		model.addAttribute("searchModel", new PersonNameFilter());
		return "admin/admin";
	}
	@PostMapping("/{userId}/bann")
	@PreAuthorize("hasRole('ADMIN')")
	public String doBan(Principal principal, @PathVariable("userId") int userId) {
		Person person = userService.finByUserEmail(principal.getName());
		if(person==null) return"redirect:/";
		Person user = userService.findByUserId(userId);
		user.setBanned(true);
		userService.editUser(user);
		
		return "redirect:/user/"+user.getId();
	}
	@PostMapping("/{userId}/outbann")
	@PreAuthorize("hasRole('ADMIN')")
	public String doOutBan(Principal principal, @PathVariable("userId") int userId) {
		Person person = userService.finByUserEmail(principal.getName());
		if(person==null) return"redirect:/";
		Person user = userService.findByUserId(userId);
		user.setBanned(false);
		userService.editUser(user);
		
		return "redirect:/user/"+user.getId();
	}
	@PostMapping("/{userId}/delete")
	@PreAuthorize("hasRole('ADMIN')")
	public String userDelete(Principal principal, @PathVariable("userId") int userId) {
		Person person = userService.finByUserEmail(principal.getName());
		if(person==null) return"redirect:/";
		Person user = userService.findByUserId(userId);
		if(user==null) return"redirect:/";
		userService.delete(userId);
		
		return "redirect:/user/";
	}
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{userId}/edit")
	public String aditUser(Principal principal,Model model,@PathVariable("userId") int userId) {
		Person person = userService.finByUserEmail(principal.getName());
		if(person==null) return"redirect:/";
		Person user = userService.findByUserId(userId);
		EditUserRequest request = UserMapper.editUserProfile(user);
		PhotoRequest photorequest = new PhotoRequest();
		model.addAttribute("editModel", request);
		model.addAttribute("males", Male.values());
		model.addAttribute("countrys", Country.values());
		model.addAttribute("editPhotoModel",photorequest);
		return"admin/edit";
	}
	@PostMapping("/{userid}/edit")
	public String saveEditedProfile(
			@ModelAttribute("editModel")@Valid EditUserRequest request,Principal principal,BindingResult result
			)  {
		if(result.hasErrors()) {
			return "redirect:/{userid}/edit";
		}
		Person entity = UserMapper.editRequestEntity(request);
		userService.editUser(entity);		
		return "redirect:/user/"+ entity.getId();
	}
	@PostMapping("{userId}/edit/photo")
	public String changePhoto(@ModelAttribute("editPhotoModel") PhotoRequest photorequest,Principal principal,@PathVariable("userId") int userId) throws IOException {
		Person entity =userService.findByUserId(userId);
		Photo photo = PhotoMapper.UploadPhotoRequest(photorequest);
		photo.setPerson(entity);
		List<PhotoAlbum> albums = entity.getPhotoAlbum();
		boolean is=true;
		for (PhotoAlbum photoAlbum : albums) {
			if(photoAlbum.isMainAlbum()) {
				photo.setPhotoAlbum(photoAlbum);
				is=false;
			}
		}
		if(is) {
		PhotoAlbum photoAlbum = new PhotoAlbum();
		photoAlbum.setPerson(entity);
		photoAlbum.setMainAlbum(true);
		photo.setPhotoAlbum(photoAlbum);
		photoAlbumService.save(photoAlbum);
		}
		
		CustomFileUtils.createFolder("user_" + entity.getId());
		CustomFileUtils.createImage("user_" + entity.getId(), photorequest.getFile());
		photoService.save(photo);
		return "redirect:/user/"+ entity.getId();
	}

}
