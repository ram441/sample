/*package com.itcinfotech.pbb.admin.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.itcinfotech.pbb.admin.service.EmailService;
@Service("emailService")
public class EmailServiceImpl implements EmailService{
	@Autowired
	private org.springframework.mail.javamail.JavaMailSender mailSender;
	
	@Async
	public void sendEmail(SimpleMailMessage email) {
		mailSender.send(email);
	}

}
*/