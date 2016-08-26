package com.cognizone.tests.manju.messageapi2.services;

import java.math.BigInteger;
import java.util.Collection;

import com.cognizone.tests.manju.messageapi2.model.Message;

public interface MessageService {

	
	public Message saveMessage(Message message);
	
	
	public Collection<Message> getAllMessage();
	
	
	public Message getMessageById(BigInteger id);
}
