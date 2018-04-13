package com.ua.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ua.dto.ChangePassDto;
import com.ua.dto.RegisterRequest;
import com.ua.entity.Person;
import com.ua.entity.enums.Male;
import com.ua.mail.Mail;
import com.ua.mapper.UserMapper;
import com.ua.service.EmailService;
import com.ua.service.UserService;
import com.ua.token.RandomToken;


@Controller

public class BaseController {
	@Autowired 
	private UserService userService;
	@Autowired
	private EmailService mailService;
	
	@GetMapping("/register")
	public String showRegister(Model model) {
		model.addAttribute("registerModel", new RegisterRequest());
		model.addAttribute("males",Male.values());
		return "register";
	}


	
	@PostMapping("/register")
	public String createUser(
			@ModelAttribute("registerModel") @Valid RegisterRequest registerRequest,
			BindingResult result) {
		
		if(result.hasErrors()) {
			return "register";
		}
		Person user = UserMapper.userRegister(registerRequest);
		String token = RandomToken.generedeRandomString();
		user.setToken(token);
		user.setActivated(false);
		userService.saveUser(user);
		Mail mail = new Mail();
		mail.setTo(registerRequest.getEmail());
		mail.setSubject("You are succesfuly regisered");
		mail.setContent("good job please verify your email " + "http://localhost:8080/verify?token="+token +"&userid="+user.getId());
		mailService.sendMessage(mail);
		return "redirect:/login";
	}
	
	
	@GetMapping({"/login","/"})
	public String showLogin() {
		
		return "login";
	}
	@GetMapping("/logexit")
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	   if (auth != null){    
	       new SecurityContextLogoutHandler().logout(request, response, auth);
	   }
	   return "redirect:/login";
	}
	@GetMapping("/isAvalible")
	public String tryLogin(Principal principal) {
		Person  person = userService.finByUserEmail(principal.getName());
		if(person.isActivated()==false) return "redirect:/logexit";
		if(person.isBanned()==true) return "redirect:/logexit";
		else return "redirect:/user";
	}
	
	@GetMapping("/verify")
	public String verifyUserEmail(@RequestParam("token") String token, @RequestParam("userid") String userIdstr,
			Model  model) {
		int userid = Integer.valueOf(userIdstr);
		Person user = userService.findByUserId(userid);
		if(user.getToken().equals(token)) {
			user.setToken("");
			user.setActivated(true);
			userService.updateUser(user);
			model.addAttribute("userEmail",user.getEmail());
			return "succes";
		}
		return "redirect:/login";
		
	}
	@GetMapping("/changepass")
	public String changePassword(@RequestParam("token") String token, @RequestParam("userid") String userIdstr,
			Model  model) {
		int userid = Integer.valueOf(userIdstr);
		Person person = userService.findByUserId(userid);
		
		if(person.getToken().equals(token)) {
			
			model.addAttribute("changePass",new ChangePassDto());
			return "tools/changepass";
		}
		return "redirect:/login";
		
	}
	@PostMapping("/changepass/change")
	public String chPassword(
			@ModelAttribute("changePass") @Valid ChangePassDto request,Principal principal, BindingResult result) {
		if(result.hasErrors()) {
			return "tools/changepass";
		}
		Person person = userService.finByUserEmail(principal.getName());
		person.setToken("");
		person.setPassword(request.getPassword());
		userService.saveUser(person);
		
		return "redirect:/user";
	}
	@PostMapping("/changepass/send")
	public String chPasswordsend(Principal principal) {
		Person person = userService.finByUserEmail(principal.getName());
		String token = RandomToken.generedeRandomString();
		person.setToken(token);
		Mail mail = new Mail();
		mail.setTo(person.getEmail());
		mail.setSubject("Заявка на зміну паролю");
		mail.setContent("Ви подали заявку на зміну паролю, яйкщо ви хочете змінити "
				+ " пароль перейдіть по силці: " + "http://localhost:8080/changepass?token="+token +"&userid="+person.getId());
		mailService.sendMessage(mail);
		userService.editUser(person);
		return "redirect:/send";
	}
	@GetMapping("/send")
	public String sendPass(Principal principal) {
		return "/tools/send";
	}
}
