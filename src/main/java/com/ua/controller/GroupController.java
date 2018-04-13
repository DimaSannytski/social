package com.ua.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ua.dto.PhotoRequest;
import com.ua.dto.SpilnotyRequest;
import com.ua.dto.WallComentsRequest;
import com.ua.dto.WallRequest;
import com.ua.entity.Person;
import com.ua.entity.Photo;
import com.ua.entity.Spilnoty;
import com.ua.entity.Wall;
import com.ua.entity.WallComents;
import com.ua.entity.enums.ButtonStatus;
import com.ua.entity.enums.GroupCategory;
import com.ua.mapper.PhotoMapper;
import com.ua.mapper.SpilnotyMapper;
import com.ua.mapper.WallComentsMapper;
import com.ua.mapper.WallMapper;
import com.ua.service.PhotoService;
import com.ua.service.SpilnotyService;
import com.ua.service.UserService;
import com.ua.service.WallComentsService;
import com.ua.service.WallService;
import com.ua.service.utils.CustomFileUtils;

@Controller
@SessionAttributes("editGroup")
@RequestMapping("/group")
public class GroupController {
	@Autowired UserService userService;
	@Autowired SpilnotyService spilnotyService;
	@Autowired WallService wallService;
	@Autowired WallComentsService wallComentService;
	@Autowired PhotoService photoService;
	@GetMapping
	public String showGroup(Principal principal,Model model) {
		Person person = userService.finByUserEmail(principal.getName());
		if(person==null) return"redirect:/login";
		List<Spilnoty> groups = spilnotyService.findAll();
		model.addAttribute("spilnotyAll", groups);
		model.addAttribute("category",GroupCategory.values());
		return "/group/allgroup";
	}
	@GetMapping ("/my")
	public String showMygroup(Principal principal,Model model) {
		Person person = userService.finByUserEmail(principal.getName());
		if(person==null) return"redirect:/login";
		List<Spilnoty> spilnoty = person.getSpilnotys();
		
		model.addAttribute("groupModel",spilnoty);
		
		return "/group/mygroup";
	}
	@GetMapping ("/create")
	public String createGrouo(Principal principal, Model model) {
		
		Person person = userService.finByUserEmail(principal.getName());
		if(person==null) return"redirect:/login";
		SpilnotyRequest request = new SpilnotyRequest();
		model.addAttribute("categorys",GroupCategory.values());
		model.addAttribute("createGroup",request);
		return "/group/create";
	}
	@PostMapping("/create")
	public String createGroupNew(Principal principal,@ModelAttribute("createGroup")@Valid SpilnotyRequest request,BindingResult result) {
		if(result.hasErrors()) {
			return "redirect:/create";
		}
		Person person = userService.finByUserEmail(principal.getName());
		if(person==null) return"redirect:/login";
		Spilnoty spilnoty = SpilnotyMapper.createSpilnotyRequest(request);
		List<Person> persons = new ArrayList<Person>();
		List<Spilnoty>spilnotys = person.getSpilnotys();
		spilnotys.add(spilnoty);
		person.setSpilnotys(spilnotys);
		persons.add(person);
		spilnoty.setPersons(persons);
		spilnoty.setAdmin(person);
		spilnotyService.save(spilnoty);
		return "redirect:/group/"+spilnoty.getId();
	}
	@GetMapping("/{groupId}")
	public String showOneGroup(@PathVariable("groupId") int groupId,Principal principal,Model model) throws IOException {
		Person person = userService.finByUserEmail(principal.getName());
		Spilnoty spilnoty = spilnotyService.findOne(groupId);
		List<Person> persons = spilnoty.getPersons();
		boolean buttonShow = false;
		if(person==spilnoty.getAdmin()) buttonShow=true;
		ButtonStatus buttonStatus=ButtonStatus.SHOW;
		for (Person user : persons) {
			if(user==person) {
				buttonStatus=ButtonStatus.HIDE;
			}
		}
		SpilnotyRequest spilnotyRequest = SpilnotyMapper.spilnotyToRequest(spilnoty);
		WallRequest wallRequest = new WallRequest();
		WallComentsRequest wallComentsRequest = new WallComentsRequest();
		List<Wall> wallPosts = spilnoty.getWalls();
		wallPosts.sort(new Comparator<Wall>() {

			@Override
			public int compare(Wall o1, Wall o2) {
				if(o1.getId()<o2.getId()) return 1;
				else return -1;
			}
			
		});
		for (Wall wall : wallPosts) {
			Person wallperson = wall.getFromPerson();
			String image = wallperson.getPhoto();
			if(image==null) wallperson.setPhoto(" ");
			if(image.length()<45) {
			wallperson.setPhoto(
					CustomFileUtils.getImage("user_" + wallperson.getId(), image));
			
			}}
		List<Photo> photo = spilnoty.getPhotos();
		model.addAttribute("wallComentModel",wallComentsRequest);
		model.addAttribute("showButton",buttonShow);
		model.addAttribute("spilnotyModel",spilnotyRequest);
		model.addAttribute("wallModel",wallRequest);
		model.addAttribute("wallPosts",wallPosts);
		model.addAttribute("buttonStatus",buttonStatus);
		if(photo.size()!=0) {
		model.addAttribute("photoId",photo.get(photo.size()-1).getId());
		
		model.addAttribute("imageSrc",
				CustomFileUtils.getImage("group_" + spilnoty.getId(), photo.get(photo.size()-1).getFile()));
		} else {
			model.addAttribute("imageSrc",
					CustomFileUtils.getImage("group_null" + spilnoty.getId(), spilnoty.getPhoto()));
		}
		return"group/group";
	}
	@PostMapping("/{groupId}/send")
	public String sendWall(@PathVariable("groupId") int groupId,Principal principal,@ModelAttribute("wallModel")@Valid WallRequest request,BindingResult result) {
		if(result.hasErrors()) {
			return "redirect:/group/{groupId}";
		}
		Person person = userService.finByUserEmail(principal.getName());
		Wall wall = WallMapper.sendWallPost(request);
		wall.setFromPerson(person);
		wall.setSpilnoty(spilnotyService.findOne(groupId));
		wallService.saveWall(wall);
		return"redirect:/group/{groupId}";
	}
	@PostMapping("/{groupId}/{wallId}/send")
	public String sendWallComent(@PathVariable("wallId") int wallId,Principal principal,@ModelAttribute("wallComentModel")@Valid WallComentsRequest request,BindingResult result) {
		if(result.hasErrors()) {
			return "redirect:/group/{groupId}";
		}
		Person person = userService.finByUserEmail(principal.getName());
		WallComents wallComents = WallComentsMapper.sendWallComentPost(request);
		wallComents.setWall(wallService.finById(wallId));
		wallComents.setPerson(person);
		wallComentService.save(wallComents);
		return"redirect:/group/{groupId}";
	}
	@PostMapping("/{groupId}/join")
	public String joinToGroup(@PathVariable("groupId") int groupId,Principal principal){
		Person person = userService.finByUserEmail(principal.getName());
		Spilnoty spilnoty = spilnotyService.findOne(groupId);
		List<Person> persons = spilnoty.getPersons();
		for(int i =0; i<persons.size();i++) {
			if(persons.get(i)==person) {
				return "redirect:/group/{groupId}";
			}
		}
		persons.add(person);
		List<Spilnoty> spilnotys =person.getSpilnotys();
		spilnotys.add(spilnoty);
		person.setSpilnotys(spilnotys);
		spilnotyService.save(spilnoty);
		return"redirect:/group/{groupId}";
	}
	@PostMapping("/{groupId}/out")
	public String щгеToGroup(@PathVariable("groupId") int groupId,Principal principal){
		Person person = userService.finByUserEmail(principal.getName());
		Spilnoty spilnoty = spilnotyService.findOne(groupId);
		List<Person> persons = spilnoty.getPersons();
//		for(int i =0; i<persons.size();i++) {
//			if(persons.get(i)==person) {
//				return "redirect:/group/{groupId}";
//			}
//		}
		Iterator<Person> itb = persons.iterator();
		while(itb.hasNext()) {
			Person persong = itb.next();
			if(persong==person) {
				itb.remove();
			}
		}
		List<Spilnoty> spilnotys =new ArrayList<Spilnoty>();
		spilnoty.setPersons(persons);
		person.setSpilnotys(spilnotys);
		spilnotyService.save(spilnoty);
		return"redirect:/group/{groupId}";
	}
	@GetMapping("/{groupId}/settings")
	public String showAdmin(Principal principal,Model model,@PathVariable("groupId") int groupId) {
		Person person = userService.finByUserEmail(principal.getName());
		Spilnoty spilnoty = spilnotyService.findOne(groupId);
		if(person!=spilnoty.getAdmin()) return "redirect:/group";
		SpilnotyRequest request = SpilnotyMapper.spilnotyToRequest(spilnoty);
		PhotoRequest photoRequest = new PhotoRequest();
		model.addAttribute("editGroup", request);
		model.addAttribute("photoModel",photoRequest);
		return "group/admin";
	}
	@PostMapping ("/{groupId}/settings/confirm")
	public String confirmSettings(Principal principal, @ModelAttribute("editGroup")@Valid SpilnotyRequest request,@PathVariable("groupId") int groupId,BindingResult result) throws IOException {
		if(result.hasErrors()) {
			return "redirect:/{groupId}/settings";
		}
		Person person = userService.finByUserEmail(principal.getName());
		Spilnoty spilnoty = spilnotyService.findOne(groupId);
		if(person!=spilnoty.getAdmin()) return "redirect:/group";
	//	Spilnoty spilnoty = SpilnotyMapper.editSpilnotyRequest(request);
		spilnoty.setAbout(request.getAbout());
		spilnoty.setGroupName(request.getGroupName());
		spilnotyService.save(spilnoty);
		
		return "redirect:/group/{groupId}";
	}
	@PostMapping ("/{groupId}/settings/photo")
	public String confirmSettingsPhoto(Principal principal, @ModelAttribute("photoModel") PhotoRequest photoRequest,@PathVariable("groupId") int groupId) throws IOException {
		Person person = userService.finByUserEmail(principal.getName());
	
		Spilnoty spilnoty = spilnotyService.findOne(groupId);
		if(person!=spilnoty.getAdmin()) return "redirect:/group";
		Photo photo = PhotoMapper.UploadPhotoRequest(photoRequest);
		photo.setSpilnoty(spilnoty);
		CustomFileUtils.createFolder("group_" + spilnoty.getId());
		CustomFileUtils.createImage("group_" + spilnoty.getId(), photoRequest.getFile());
		System.out.println("dkjghk"+photo.getSpilnoty());
		photoService.save(photo);
		
		return "redirect:/group/{groupId}";
	}
	

}
