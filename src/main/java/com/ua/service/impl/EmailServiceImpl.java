package com.ua.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ua.mail.Mail;
import com.ua.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	JavaMailSender javaMailSender;
	@Override
	public void sendMessage(Mail mail) {
		SimpleMailMessage messege = new SimpleMailMessage();
		messege.setFrom(mail.getFrom());
		messege.setTo(mail.getTo());
		messege.setSubject(mail.getSubject());
		messege.setText(mail.getContent());
		
		javaMailSender.send(messege);
	
	}
	
	

}
