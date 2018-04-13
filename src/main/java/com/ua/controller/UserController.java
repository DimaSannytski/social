package com.ua.controller;

import java.io.IOException;
import java.security.Principal;
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

import com.ua.dto.DialogRequest;
import com.ua.dto.EditUserRequest;
import com.ua.dto.FriendRequest;
import com.ua.dto.PersonNameFilter;
import com.ua.dto.PhotoRequest;
import com.ua.dto.UserProfileRequest;
import com.ua.dto.WallComentsRequest;
import com.ua.dto.WallRequest;
import com.ua.entity.Dialog;
import com.ua.entity.Friend;
import com.ua.entity.Person;
import com.ua.entity.Photo;
import com.ua.entity.PhotoAlbum;
import com.ua.entity.Wall;
import com.ua.entity.WallComents;
import com.ua.entity.enums.ButtonStatus;
import com.ua.entity.enums.Country;
import com.ua.entity.enums.FriendStatus;
import com.ua.entity.enums.Male;
import com.ua.mapper.DialogMapper;
import com.ua.mapper.FriendMapper;
import com.ua.mapper.PhotoMapper;
import com.ua.mapper.UserMapper;
import com.ua.mapper.WallComentsMapper;
import com.ua.mapper.WallMapper;
import com.ua.service.DialogService;
import com.ua.service.FriendService;
import com.ua.service.PhotoAlbumService;
import com.ua.service.PhotoService;
import com.ua.service.UserService;
import com.ua.service.WallComentsService;
import com.ua.service.WallService;
import com.ua.service.utils.CustomFileUtils;

@RequestMapping("/user")
@SessionAttributes({"editModel","editPhotoModel"})

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private WallService wallService;
	@Autowired
	private WallComentsService wallComentsService;
	@Autowired
	private DialogService dialogService;
	@Autowired private FriendService friendService;
	@Autowired private PhotoAlbumService photoAlbumService;
	@Autowired private PhotoService photoService;
	
	@GetMapping
	public String redirectPage(Principal principal,Model model) {
		Person person = userService.finByUserEmail(principal.getName());
		return "redirect:/user/"+person.getId();
	}

	@GetMapping("/{userId}")
	public String showAnotherUserProfile(@PathVariable("userId") int userId,Model model, Principal principal) throws IOException {
		Person entity = userService.findByUserId(userId);	
        Person person = userService.finByUserEmail(principal.getName());
		if(entity == null||person==null) return "redirect:/";
		ButtonStatus buttonStatus=ButtonStatus.SHOW;
		ButtonStatus buttonBanStatus=ButtonStatus.SHOW;
		boolean buttonShow = false;
		if(entity.isBanned()) buttonBanStatus=ButtonStatus.HIDE;
		List<Friend> friends = entity.getFriendsa();
		
		friends.addAll(entity.getFriendsb());
		int friendId=0;
		if(entity==person) {
			buttonStatus=ButtonStatus.HIDE;
		} else {
		for (Friend friend : friends) {
			
			if(friend.getAfriend()==person||friend.getBfriend()==person) {
				if(friend.getFriendStatus()==FriendStatus.ACTIVE) buttonStatus=ButtonStatus.FRIEND;
				if(friend.getFriendStatus()==FriendStatus.WAITING) {
					buttonStatus=ButtonStatus.CONFIRM;
					friendId=friend.getId();
				}
			}
			if(friend.getAfriend()==entity) {
				if(friend.getFriendStatus()==FriendStatus.WAITING) {
					buttonStatus=ButtonStatus.SEND;
					friendId=friend.getId();
				}
			}
			
		}}
		List<Wall> wallPosts =entity.getWall();
		wallPosts.sort(new Comparator<Wall>() {

			@Override
			public int compare(Wall o1, Wall o2) {
				if(o1.getId()<o2.getId()) return 1;
				else return -1;
			}
			
		});
		for (Wall wall : wallPosts) {
			Person wallperson = wall.getFromPerson();
			if(wallperson.getPhoto()==null) wallperson.setPhoto(" ");
			if((wallperson.getPhoto().length()<50)) {
			wallperson.setPhoto(
					CustomFileUtils.getImage("user_" + wallperson.getId(), wallperson.getPhoto()));
			
			}
			}
		
		UserProfileRequest request = UserMapper.entityToUserProfile(entity);
		DialogRequest dialogRequest = new DialogRequest();
		WallRequest wallRequest  = new WallRequest();
		WallComentsRequest wallComentsRequest = new WallComentsRequest();
		
		FriendRequest friendRequest = new FriendRequest();
		List<Friend> frienda = entity.getFriendsa();
		
       Iterator<Friend> it = frienda.iterator();
       
		while(it.hasNext()) {
			Friend friendn = it.next();
			if(friendn.getFriendStatus()!=FriendStatus.ACTIVE||friendn.getBfriend()==entity) {
				it.remove();
			}
		}
		List<Friend> friendsb = entity.getFriendsb();
		Iterator<Friend> itb = friendsb.iterator();
		
		while(itb.hasNext()) {
			Friend friendne = itb.next();
			if(friendne.getFriendStatus()!=FriendStatus.ACTIVE) {
				itb.remove();
			}
		}
		System.out.println(frienda.size());
		model.addAttribute("friendA",frienda);
		model.addAttribute("friendB",friendsb);
		model.addAttribute("buttonShow", buttonShow);
		model.addAttribute("friendId",friendId);
		model.addAttribute("buttonStatus",buttonStatus.toString());
		model.addAttribute("buttonBanStatus", buttonBanStatus);
		model.addAttribute("userProfile", request);
		model.addAttribute("personId",person);
		model.addAttribute("friendModel",friendRequest);
		model.addAttribute("wallPosts", wallPosts);
		model.addAttribute("wallModel",wallRequest);
		model.addAttribute("dialogModel",dialogRequest);
		model.addAttribute("commentModel",wallComentsRequest);
		
		
		List<PhotoAlbum> albums = entity.getPhotoAlbum();
		boolean is = true;
		int avaId=0;
		for (PhotoAlbum photoAlbum : albums) {
			if(photoAlbum.isMainAlbum()) {
				int size = photoAlbum.getPhoto().size()-1;
				if(size<0) break;
				 avaId = photoAlbum.getPhoto().get(size).getId();
				is=false;
				
				 break;
				
			}
		}

		if(is) {
			model.addAttribute("imageSrc",
					CustomFileUtils.getImage("user_" + entity.getId(), ""));
		}else {
			model.addAttribute("imageSrc",
					CustomFileUtils.getImage("user_" + entity.getId(), entity.getPhoto()));
		}
		model.addAttribute("avaId",avaId);
		return "user/page";
	}

	@GetMapping("/all")
	public String showAllUsers(Principal principal, Model model) throws IOException {
		List<Person> persons = userService.findAll();
		for (Person person : persons) {
			person.setPhoto(
					CustomFileUtils.getImage("user_" + person.getId(), person.getPhoto())
					);
		}
		model.addAttribute("searchModel", new PersonNameFilter());
		model.addAttribute("usersModel",persons);
		return"/user/all";
	}
	@PostMapping("/{userId}/sendWall")
	public String sendWallPost( @PathVariable("userId") int userId,Principal principal,@ModelAttribute("wallModel")@Valid WallRequest wallRequest,BindingResult result) {
		if(result.hasErrors()) {
			return "redirect:/user/{userId}";
		}
		Person entity = userService.finByUserEmail(principal.getName());
		Wall wall = WallMapper.sendWallPost(wallRequest);	
		wall.setPerson(userService.findByUserId(userId));
		wall.setFromPerson(entity);
		wallService.saveWall(wall);
		
		return "redirect:/user/{userId}";
		
	}
	@PostMapping("/{wallId}/deletewall")
	public String deleteWall(Principal principal,@PathVariable("wallId") int wallId) {
		Person person = userService.finByUserEmail(principal.getName());
		if(person==null) return "redirect:/";
		Wall wall = wallService.finById(wallId);
		if(person!=wall.getFromPerson()&&person!=wall.getPerson()) return "redirect:/";
		wallService.delete(wall.getId());
		return "redirect:/user/"+wall.getPerson().getId();
	}
	@PostMapping("/{userId}/wall/{wall.id}/comment")
	public String sendWallComent(@PathVariable("userId") int userId,@PathVariable("wall.id") int wallId, Principal principal,@ModelAttribute("commentModel")@Valid WallComentsRequest request,BindingResult result) {
		if(result.hasErrors()) {
			return "recirect:/user/{userId}";
		}
		Person entity = userService.finByUserEmail(principal.getName());
		WallComents wallComents = WallComentsMapper.sendWallComentPost(request);	
		wallComents.setPerson(userService.findByUserId(entity.getId()));
		wallComents.setWall(wallService.finById(wallId));
		wallComentsService.save(wallComents);
		
		return "redirect:/user/{userId}";
		
	}
	@PostMapping("/{userId}/friend")
	public String addToFriend(@PathVariable("userId") int userId,Principal principal,@ModelAttribute("friendModel") FriendRequest request) {
		Person aPerson = userService.finByUserEmail(principal.getName());
		Person bPerson = userService.findByUserId(userId);
		List<Friend> friends = aPerson.getFriendsb();
		List<Friend> afriends = bPerson.getFriendsb();
		if(aPerson==bPerson) {
			
					return "redirect:/user/{userId}";
				} 
		for(int i=0;i<friends.size();i++) {
			if(friends.get(i).getAfriend()==bPerson&&friends.get(i).getAfriend()!=friends.get(i).getBfriend()) {
				
				return "redirect:/user/{userId}";
				
			}
			}
		for(int j=0;j<afriends.size();j++) {
			if(afriends.get(j).getAfriend()==aPerson&&afriends.get(j).getAfriend()!=afriends.get(j).getBfriend()) {
				
				return "redirect:/user/{userId}";
			}}
		request.setBfriend(aPerson);
		request.setAfriend(bPerson);
		Friend friend = FriendMapper.addFriend(request);
		friendService.save(friend);
		return "redirect:/user/{userId}";
		
	}
	@PostMapping("/{userId}/dialog")
	public String createDialog(@PathVariable("userId") int userId,Principal principal,@ModelAttribute("dialogModel") DialogRequest request) {
		Person aPerson = userService.finByUserEmail(principal.getName());
		Person bPerson = userService.findByUserId(userId);
		List<Dialog> dialogs = aPerson.getADialog();
		List<Dialog> dialogsB = bPerson.getADialog();
		if(aPerson==bPerson) {
			for(int i=0;i<dialogs.size();i++) {
				if(dialogs.get(i).getBPerson()==bPerson) {
					return "redirect:/mail/dialog/"+dialogs.get(i).getId();
				} }
		}
		for(int i=0;i<dialogs.size();i++) {
			if(dialogs.get(i).getBPerson()==bPerson&&dialogs.get(i).getBPerson()!=dialogs.get(i).getAPerson()) {
				return "redirect:/mail/dialog/"+dialogs.get(i).getId();
				
			}
			}
		for(int j=0;j<dialogsB.size();j++) {
			if(dialogsB.get(j).getBPerson()==aPerson&&dialogsB.get(j).getBPerson()!=dialogsB.get(j).getAPerson()) {
				return "redirect:/mail/dialog/"+dialogsB.get(j).getId();
			}}
		
			
			request.setAPerson(aPerson);
			request.setBPerson(bPerson);
			Dialog dialog = DialogMapper.createDialogRequest(request);
			dialogService.save(dialog);
			return "redirect:/mail/dialog/"+dialog.getId().intValue();
			
		
	}
	@GetMapping("/edit/")
	public String editUserProfile(
			Model model, Principal principal) {
		Person entity = userService.finByUserEmail(principal.getName());
		EditUserRequest request = UserMapper.editUserProfile(entity);
		PhotoRequest photorequest =new PhotoRequest();
		model.addAttribute("editModel", request);
		model.addAttribute("males", Male.values());
		model.addAttribute("countrys", Country.values());
		model.addAttribute("editPhotoModel",photorequest);
		return "user/edit";
	}
	
	@PostMapping("/edit/save")
	public String saveEditedProfile(
			@ModelAttribute("editModel")@Valid EditUserRequest request,Principal principal,BindingResult result
			) {
		if(result.hasErrors()) {
			return "redirect:/user/edit";
		}
		Person entity = UserMapper.editRequestEntity(request);
		userService.editUser(entity);		
		
		return "redirect:/user";
	}
	@PostMapping("/edit/photo")
	public String changePhoto(@ModelAttribute("editPhotoModel") PhotoRequest photorequest,Principal principal) throws IOException {
		Person entity = userService.finByUserEmail(principal.getName());
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
		photoAlbum.setName("Фото профілю");
		photoAlbum.setMainAlbum(true);
		photo.setPhotoAlbum(photoAlbum);
		photoAlbumService.save(photoAlbum);
		}
		
		CustomFileUtils.createFolder("user_" + entity.getId());
		CustomFileUtils.createImage("user_" + entity.getId(), photorequest.getFile());
		entity.setPhoto(photo.getFile());
		photoService.save(photo);
		return "redirect:/user";
	}

}
