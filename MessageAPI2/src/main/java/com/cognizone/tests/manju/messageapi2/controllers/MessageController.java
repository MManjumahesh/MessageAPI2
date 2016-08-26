package com.cognizone.tests.manju.messageapi2.controllers;

import java.math.BigInteger;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognizone.tests.manju.messageapi2.model.Message;
import com.cognizone.tests.manju.messageapi2.services.MessageService;

@RestController
public class MessageController {

	private static Logger logger = LoggerFactory.getLogger("MessageController");

	private MessageService messageService;

	@Autowired
	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}

	@RequestMapping(value = "/api/Messages", 
					method = RequestMethod.GET, 
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Message>> getAllMessages() {
		logger.info("Received Request for getAllMessages");

		Collection<Message> students = messageService.getAllMessage();
		return new ResponseEntity<Collection<Message>>(students, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/Messages/{id}", 
					method = RequestMethod.GET, 
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> getMessage(@PathVariable("id") BigInteger id) {

		logger.info("Received Request for getMessage(" + id + ")");

		Message message = messageService.getMessageById(id);
		if (message == null) {
			return new ResponseEntity<Message>(HttpStatus.NOT_FOUND);
		}

		logger.debug("Message retrived :" + message);
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/Messages", 
					method = RequestMethod.POST, 
					consumes = MediaType.APPLICATION_JSON_VALUE, 
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> createMessage(@RequestBody Message message) {

		logger.info("Received Request for createMessage(" + message.getMessage() + ")");
		Message savedMessage = messageService.saveMessage(message);

		return new ResponseEntity<Message>(savedMessage, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/api/Messages/{id}", 
					method = RequestMethod.DELETE, 
					consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> deleteMessage(@PathVariable("id") BigInteger id, @RequestBody Message message) {

		logger.info("Received Request for deleteMessage(" + id + ")");
		
		boolean isDeleted = messageService.deleteMessageById(id);
		
		if(!isDeleted){
			return new ResponseEntity<Message>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Message>( HttpStatus.OK);
	}
}
