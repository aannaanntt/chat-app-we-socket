package com.chat.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class Config implements WebSocketMessageBrokerConfigurer{

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/server1").withSockJS();
		
	}

	@Override
	//configure to send and receive
	public void configureMessageBroker(MessageBrokerRegistry registry)  {
		registry.enableSimpleBroker("/topic"); //this will enable brocker on this URL
		registry.setApplicationDestinationPrefixes("/app");
	}
	
	
	

}
