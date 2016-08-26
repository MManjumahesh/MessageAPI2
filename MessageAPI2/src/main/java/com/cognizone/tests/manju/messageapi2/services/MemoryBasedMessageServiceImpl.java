package com.cognizone.tests.manju.messageapi2.services;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.cognizone.tests.manju.messageapi2.model.Message;

@Service
@Primary
public class MemoryBasedMessageServiceImpl implements MessageService{

	
	private static final Logger logger = LoggerFactory.getLogger(FileBasedMessageServiceImpl.class);
	
	private static BigInteger nextId;

	private static Map<BigInteger, Message> messagesMap;

	
	static {

		Message message1 = new Message();
		message1.setMessage("Sample Message");
		
		MemoryBasedMessageServiceImpl impl = new MemoryBasedMessageServiceImpl();
		impl.saveMessage(message1);
	}

	@Override
	public Message saveMessage(Message message) {
		if (messagesMap == null) {
			messagesMap = new HashMap<BigInteger, Message>();
			nextId = BigInteger.ONE;
		}else{
			
			nextId = (message.getId() == null)?nextId.add(BigInteger.ONE): message.getId();
			
		
		}

		message.setId(nextId);
		messagesMap.put(nextId, message);

		return message;
	}

	@Override
	public Collection<Message> getAllMessage() {
		Collection<Message> students = messagesMap.values();
		return students;
	}

	@Override
	public Message getMessageById(BigInteger id) {
		Message message = messagesMap.get(id);
		logger.debug("Message retrived :"+ message);
		
		return message;
	}
}
