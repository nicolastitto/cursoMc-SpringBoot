package com.trabalho.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.trabalho.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
