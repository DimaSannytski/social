package com.ua.controller;

import java.security.Principal;
import java.util.Comparator;
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

import com.ua.dto.MyMessageRequest;
import com.ua.entity.Dialog;
import com.ua.entity.MyMessage;
import com.ua.entity.Person;
import com.ua.mapper.MyMessageMapper;
import com.ua.service.DialogService;
import com.ua.service.MyMessageService;
import com.ua.service.UserService;

@RequestMapping("/mail")
@Controller
public class MessageController {
	@Autowired
	private UserService userService;
	@Autowired 
	private MyMessageService messageService;
	@Autowired
	private DialogService dialogService;
	@GetMapping
	public String showMail(Principal principal,Model model) {
		Person person = userService.finByUserEmail(principal.getName());
		List<Dialog> dialogs = person.getADialog();
		List<Dialog> dialogsB = person.getBDialog();
		
		for(int i =0; i<dialogsB.size();i++) {
			if(dialogsB.get(i).getAPerson()==dialogsB.get(i).getBPerson()) {
				dialogsB.remove(i);
				break;
			}
		}
			dialogs.addAll(dialogsB);
		/*for(int i = 0;i<dialogs.size();i++) {
			List<MyMessage> mess = dialogs.get(i).getMyMessage();
			LastMessage lastMessage = MyMessageMapper.messToLas(dialogs.get(i).getMyMessage().get(mess.size()));
			dialogs.get(i).setLastMessage(lastMessage);
		}*/
//			List<MyMessage> messages = new ArrayList<>();
//			for (Dialog dialog : dialogs) {
//				messages.add(dialog.getMyMessage().get(dialog.getMyMessage().size()-1));
//				
//			}
//			model.addAttribute("messageList",messages);
		model.addAttribute("dialogModel",dialogs);
		
		return"mail/mail";
	}
	@GetMapping("/dialog/{dialogId}")
	public String showDialog(@PathVariable("dialogId") int dialogId,Principal principal,Model model) {
		Person person = userService.finByUserEmail(principal.getName());
		if(dialogService.findOne(dialogId).getAPerson()!=person&&dialogService.findOne(dialogId).getBPerson()!=person) return "redirect:/mail";
		List<MyMessage> myMessages = dialogService.findOne(dialogId).getMyMessage();

		myMessages.sort(new Comparator<MyMessage>() {

			@Override
			public int compare(MyMessage o1, MyMessage o2) {
				if(o1.getId()<o2.getId()) return 1;
				else return -1;
			}
			
		});
		
		MyMessageRequest messageRequest = new MyMessageRequest();
		model.addAttribute("messagesModel", myMessages);
		model.addAttribute("messageModel",messageRequest);
		model.addAttribute("personId", person.getId());
		
		return "mail/dialog";
	}
	@PostMapping("/dialog/{dialogId}/send")
	public String sendMessage(@PathVariable("dialogId") int dialogId,Principal principal,@ModelAttribute("messageModel")@Valid MyMessageRequest request,BindingResult result) {
		if(result.hasErrors()) {
			return "redirect:/mail/dialog/"+dialogId;
		}
		Person person = userService.finByUserEmail(principal.getName());
		Dialog dialog = dialogService.findOne(dialogId);
		if(dialogService.findOne(dialogId).getAPerson()!=person&&dialogService.findOne(dialogId).getBPerson()!=person) return "redirect:/mail";
		request.setAPerson(person);
		request.setDialog(dialog);
		MyMessage myMessage = MyMessageMapper.messageCreateRequest(request);
		messageService.save(myMessage);
		return "redirect:/mail/dialog/"+dialog.getId().intValue();
	}

}
