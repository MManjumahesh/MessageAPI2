package com.cognizone.tests.manju.messageapi2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.cognizone.tests.manju.messageapi2.services.MemoryBasedMessageServiceImpl;
import com.cognizone.tests.manju.messageapi2.services.MessageService;

@Configuration
public class MessageConfig {
	
	@Bean
	@Profile("dev")
	public MessageService memoryBasedMessageServiceImpl(){
		return new MemoryBasedMessageServiceImpl();
	}
	
	@Bean
	@Profile("!dev")
	public MessageService fileBasedMessageServiceImpl(){
		return new MemoryBasedMessageServiceImpl();
	}

}
