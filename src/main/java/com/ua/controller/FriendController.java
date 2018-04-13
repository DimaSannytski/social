package com.ua.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ua.entity.Friend;
import com.ua.entity.Person;
import com.ua.entity.enums.FriendStatus;
import com.ua.service.FriendService;
import com.ua.service.UserService;
import com.ua.service.utils.CustomFileUtils;

@Controller
@RequestMapping("/friend")
public class FriendController {
	@Autowired UserService userService;
	@Autowired FriendService friendService;
	@GetMapping 
	public String showFriends(Model model, Principal principal) throws IOException {
		Person person = userService.finByUserEmail(principal.getName());
		List<Friend> friendsa = person.getFriendsa();
		List<Friend> friendsb = person.getFriendsb();
		List<Friend> friends = friendService.findByAfriend(person);

		Iterator<Friend> its = friends.iterator();
		while(its.hasNext()) {
			Friend friend = its.next();
			if(friend.getFriendStatus()!=FriendStatus.WAITING) {
				its.remove();
			}
		}
		
		Iterator<Friend> it = friendsa.iterator();
		
		while(it.hasNext()) {
			Friend friendn = it.next();
			if(friendn.getFriendStatus()!=FriendStatus.ACTIVE) {
				it.remove();
			}
		}
		Iterator<Friend> itb = friendsb.iterator();
		
		while(itb.hasNext()) {
			Friend friendne = itb.next();
			if(friendne.getFriendStatus()!=FriendStatus.ACTIVE) {
				itb.remove();
			}
		}

		for (Friend friend : friendsb) {
			Person entity = friend.getAfriend();
			String image = entity.getPhoto();
			entity.setPhoto(
					CustomFileUtils.getImage("user_" + entity.getId(), image));
				
		}
		for (Friend friend : friendsa) {
			Person entity = friend.getBfriend();
			String image = entity.getPhoto();
			entity.setPhoto(
					CustomFileUtils.getImage("user_" + entity.getId(), image));
				
		}
		model.addAttribute("sizeF", friends.size());
		model.addAttribute("friendsListB",friendsb);
		model.addAttribute("friendsListA",friendsa);
		
		
		return "friend/friend";
	}
	
	@GetMapping ("/invite")
	public String showInvFriends(Model model, Principal principal) {
		Person person = userService.finByUserEmail(principal.getName());
		if(person==null) return "redirect:/";
		List<Friend> friends = friendService.findByAfriend(person);

		Iterator<Friend> it = friends.iterator();
		while(it.hasNext()) {
			Friend friend = it.next();
			if(friend.getFriendStatus()!=FriendStatus.WAITING) {
				it.remove();
			}
		}
		model.addAttribute("friendsList",friends);
		
		
		return "friend/invite";
	}
	@GetMapping ("/outinvite")
	public String showOutInvFriends(Model model, Principal principal) {
		Person person = userService.finByUserEmail(principal.getName());
		if(person==null) return "redirect:/";
		List<Friend> friends = person.getFriendsb();
		Iterator<Friend> it = friends.iterator();
		
		while(it.hasNext()) {
			Friend friend = it.next();
			if(friend.getFriendStatus()!=FriendStatus.WAITING) {
				it.remove();
			}
		}

		
//		for (Friend friend : friends) {
//			if(friend==null) break;
//			if(friend.getFriendStatus()!=FriendStatus.WAITING) {
//				friends.remove(friend);
//			}
//		}
		model.addAttribute("friendsList",friends);
		
		
		return "friend/outinvite";
	}
	@PostMapping("/invite/{friendId}")
	public String confirmInvite(Model model, Principal principal,@PathVariable("friendId") int friendId) {
		Person person = userService.finByUserEmail(principal.getName());
		Friend friend = friendService.findOne(friendId);
		if(friend.getAfriend()!=person) return "redirect:/friend/invite";
		friend.setFriendStatus(FriendStatus.ACTIVE);
		friendService.save(friend);
		return "redirect:/friend";
		
	}
	@PostMapping("/invite/{friendId}/from")
	public String confirmInviteProd(Model model, Principal principal,@PathVariable("friendId") int friendId) {
		Person person = userService.finByUserEmail(principal.getName());
		Friend friend = friendService.findOne(friendId);
		if(friend.getAfriend()!=person) return "redirect:/friend/invite";
		friend.setFriendStatus(FriendStatus.ACTIVE);
		friendService.save(friend);
		return "redirect:/user/"+friend.getBfriend().getId();
		
	}
	@PostMapping("/outfriend/{friendId}/delete")
	public String deleteAdd( Principal principal,@PathVariable("friendId") int friendId) {
		Person person = userService.finByUserEmail(principal.getName());
		Friend friend = friendService.findOne(friendId);
		if(friend.getBfriend()!=person) return "redirect:/friend/outfriend";
		friendService.delete(friend);
		return "redirect:/friend";
		
	}
	@PostMapping("/outfriend/{friendId}/delete/from")
	public String deleteAddFrom( Principal principal,@PathVariable("friendId") int friendId) {
		Person person = userService.finByUserEmail(principal.getName());
		Friend friend = friendService.findOne(friendId);
		int id = friend.getAfriend().getId();
		if(friend.getBfriend()!=person) return "redirect:/friend/outfriend";
		friendService.delete(friend);
		return "redirect:/user/"+id;
		
	}
	@PostMapping("/delete/{friendId}")
	public String deleteFriend(Model model, Principal principal,@PathVariable("friendId") int friendId) {
		Person person = userService.finByUserEmail(principal.getName());
		Friend friend = friendService.findOne(friendId);
		if(friend.getAfriend()!=person) return "redirect:/friend/invite";
		friend.setFriendStatus(FriendStatus.IGNORE);
		friendService.save(friend);
		return "redirect:/user";
		
	}
	


}
