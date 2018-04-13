package com.ua.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
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

import com.ua.dto.PhotoAlbumRequest;
import com.ua.dto.PhotoComentRequest;
import com.ua.dto.PhotoRequest;
import com.ua.entity.Person;
import com.ua.entity.Photo;
import com.ua.entity.PhotoAlbum;
import com.ua.entity.PhotoComent;
import com.ua.mapper.PhotoAlbumMapper;
import com.ua.mapper.PhotoComentMapper;
import com.ua.mapper.PhotoMapper;
import com.ua.service.PhotoAlbumService;
import com.ua.service.PhotoComentService;
import com.ua.service.PhotoService;
import com.ua.service.UserService;
import com.ua.service.utils.CustomFileUtils;

@RequestMapping("/photo")
@Controller
public class PhotoController {
	@Autowired 
	private UserService userService;
	@Autowired
	private PhotoService photoService;
	@Autowired
	private PhotoAlbumService photoAlbumService;
	@Autowired 
	private PhotoComentService photoComentService;

	
	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public String showPhoto(Principal principal, Model model) throws IOException {
		
		Person person = userService.finByUserEmail(principal.getName());
		if(person==null) return"redirect:/login";
		List<Photo> photoss = person.getPhotos();
		List<Photo> photos= new ArrayList<Photo>();
		for (Photo photo : photoss) {
			if(photo.getPhotoAlbum()==null) photos.add(photo);
		}
		List<PhotoAlbum> photoAlbums = person.getPhotoAlbum();

		for(int i = 0; i < photos.size(); i++) {
			
			String image = photos.get(i).getFile();
			photos.get(i).setFile(
					CustomFileUtils.getImage(
							"user_" + photos.get(i).getPerson().getId(), 
							image));
		}
		model.addAttribute("ShowPhotoModel",photos);
		model.addAttribute("photoAlbumModel",photoAlbums);
		
		return "photo/photo";
	}
	@GetMapping("/{photoId}")
	public String showPhoto(@PathVariable("photoId") int photoId,Principal principal ,Model model) throws IOException {
		Person person = userService.finByUserEmail(principal.getName());
		Photo photo = photoService.findOne(photoId);
		PhotoComentRequest request = new PhotoComentRequest();
		String image = photo.getFile();
		boolean showDelete=false;
		if(person==photo.getPerson()) showDelete=true;
		boolean buttonShow=true;
		for (Person pers : photo.getPersons()) {
			if(pers==person) buttonShow=false;
		}
		if(photo.getSpilnoty()!=null) {
			photo.setFile(CustomFileUtils.getImage("group_"+photo.getSpilnoty().getId(), image));
		}
		else  {
			photo.setFile(CustomFileUtils.getImage("user_"+photo.getPerson().getId(), image));
		
		}
		model.addAttribute("showDelete",showDelete);
		model.addAttribute("buttonShow",buttonShow);
		model.addAttribute("photoSrc",photo);
		model.addAttribute("comentModel", request);
		
		
		return "photo/onephoto";
	}
	@PostMapping("/{photoId}/like")
	public String doLike(Principal principal,@PathVariable("photoId") int photoId) {
		Person person = userService.finByUserEmail(principal.getName());
		if(person==null) return "redirect:/login";
		Photo photo = photoService.findOne(photoId);
		boolean is=true;
		for (Person pers : photo.getPersons()) {
			if(pers==person) is=false;
		}
		if(is) {
		photo.getPersons().add(person);
		person.getPhotoLikes().add(photo);
		}
		photoService.save(photo);
		return "redirect:/photo/{photoId}";
	}
	@PostMapping("/{photoId}/dislike")
	public String doDisLike(Principal principal,@PathVariable("photoId") int photoId) {
		Person person = userService.finByUserEmail(principal.getName());
		Photo photo = photoService.findOne(photoId);
		List<Person> persons = photo.getPersons();
		Iterator<Person> itb = persons.iterator();
		
		while(itb.hasNext()) {
			Person persong = itb.next();
			if(persong==person) {
				itb.remove();
				
			}
		}
		List<Photo> phLike = person.getPhotoLikes();
		phLike.remove(photo);
		person.setPhotoLikes(phLike);
		photo.setPersons(persons);
		
		photoService.save(photo);
		return "redirect:/photo/{photoId}";
	}
	@PostMapping("/{photoId}/send")
	public String sendComent(@PathVariable("photoId") int photoId,Principal principal ,@ModelAttribute("comentModel")@Valid PhotoComentRequest request,BindingResult result) {
		if(result.hasErrors()) {
			return "redirect:/photo/{photoId}";
		}
		Person person = userService.finByUserEmail(principal.getName());
		Photo photo = photoService.findOne(photoId);
		request.setPerson(person);
		request.setPhoto(photo);
		PhotoComent photoComent = PhotoComentMapper.addPhotoComentRequest(request);
		photoComentService.save(photoComent);
		
		
		return "redirect:/photo/{photoId}";
	}
	@GetMapping("/create/photo")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public String showCreatePhoto(Principal principal, Model model) {
		Person person = userService.finByUserEmail(principal.getName());
		PhotoRequest photoRequest = new PhotoRequest();
		photoRequest.setPerson(person);
		model.addAttribute("photoModel", photoRequest);
		return "photo/create";
	}
	@PostMapping("/create/photo")
	public String createPhoto(Principal principal,@ModelAttribute("photoModel") PhotoRequest request) throws IOException{
		Person person = userService.finByUserEmail(principal.getName());
		if (request.getFile().isEmpty()) return "redirect:/user"+person.getId(); 
		Photo photo = PhotoMapper.UploadPhotoRequest(request);
		photo.setPerson(person);
		photoService.save(photo);
		CustomFileUtils.createFolder("user_" + person.getId());
		CustomFileUtils.createImage("user_" + person.getId(), request.getFile());
		return "redirect:/photo";
	}
	@PostMapping("/{photoId}/delete")
	public String deletePhoto(Principal principal,@PathVariable("photoId") int photoId) {
		Person person = userService.finByUserEmail(principal.getName());
		if (person==null) return "redirect:/";
		Photo photo =photoService.findOne(photoId);
		if(photo.getPerson()!=person) return "redirect:/";
		photoService.delete(photo.getId());
		return"redirect:/photo";
	}
	@PostMapping("/{albumId}/deletealb")
	public String deletePhotoAlbum(Principal principal,@PathVariable("albumId") int albumId) {
		Person person = userService.finByUserEmail(principal.getName());
		if (person==null) return "redirect:/";
		PhotoAlbum photoAlbum =photoAlbumService.findOne(albumId);
		if(photoAlbum.getPerson()!=person) return "redirect:/";
		photoAlbumService.delete(albumId);
		return"redirect:/photo";
	}
   @GetMapping("/create/album")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
   public String showCreateAlbum(Principal principal ,Model model) {
	   Person person = userService.finByUserEmail(principal.getName());
	   PhotoAlbumRequest request = new PhotoAlbumRequest();
	   request.setPerson(person);
	   model.addAttribute("albumModel", request);
	   return "photo/createalb";
   }
   @GetMapping("album/create/{albId}/photo")
   public String showCreateAlbumPhoto(@PathVariable("albId") int albId,Principal principal ,Model model) {
	   Person person = userService.finByUserEmail(principal.getName());
	   PhotoRequest photoRequest = new PhotoRequest();
	   photoRequest.setPerson(person);
	   model.addAttribute("photoModel", photoRequest);
	   return "photo/crealbpho";
   }
   @PostMapping("album/create/{albId}/photo")
	public String CreateAlbumPhoto(@PathVariable("albId") int albId,Principal principal,@ModelAttribute("photoModel") PhotoRequest request) throws IOException{
		Person person = userService.finByUserEmail(principal.getName());
		if (request.getFile().isEmpty()) return "redirect:/user"+person.getId(); 
		Photo photo = PhotoMapper.UploadPhotoRequest(request);
		photo.setPerson(person);
		photo.setPhotoAlbum(photoAlbumService.findOne(albId));
		photoService.save(photo);
		CustomFileUtils.createFolder("user_" + person.getId());
		CustomFileUtils.createImage("user_" + person.getId(), request.getFile());
		return "redirect:/photo/album/{albId}";
	}
    @PostMapping("/create/album")
    public String createAlbum(Principal principal,Model model,@ModelAttribute("albumModel")@Valid PhotoAlbumRequest request,BindingResult result) {
    	if(result.hasErrors()) {
			return "redirect:/create/album";
		}
    	Person person = userService.finByUserEmail(principal.getName());
    	PhotoAlbum photoAlbum = PhotoAlbumMapper.createAlbum(request);
    	photoAlbum.setPerson(person);
    	photoAlbumService.save(photoAlbum);
    	
    	return "redirect:/photo";
    }
    @GetMapping("/album/{albId}")
    public String showAlbum(@PathVariable("albId") int albId,Principal principal, Model model) throws IOException {
    	
    	PhotoAlbum photoAlbum= photoAlbumService.findOne(albId);
    	List<Photo> photo = photoService.findAllByAlbum(photoAlbum);
    	for(int i = 0; i < photo.size(); i++) {
			String image = photo.get(i).getFile();
			photo.get(i).setFile(
					CustomFileUtils.getImage(
							"user_" + photo.get(i).getPerson().getId(), 
							image));
		}
    	model.addAttribute("isMain",photoAlbum.isMainAlbum());
    	model.addAttribute("albumModel",photo);
    	return "photo/album";
    }
    

}


