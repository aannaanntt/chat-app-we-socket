package com.chat.app.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chat.app.entity.Message;

@RestController
public class MessageController {
	
	@MessageMapping("/message")
	@SendTo("/topic/return-to") //suppose this is a utube channel and if you subscribe this
	public Message getContent(@RequestBody Message message) {
		
		try {
			//we can process all the messages here
			Thread.sleep(2000);
		} catch (InterruptedException  e) {
		System.out.println("Exception occured"+" "+e.getMessage());
		}catch (Exception e) {
			System.out.println("Exception occured"+" "+e.getMessage());
		}
		return message;
		
	}

}
