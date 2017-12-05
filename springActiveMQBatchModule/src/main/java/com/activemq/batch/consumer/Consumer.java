package com.activemq.batch.consumer;

import java.io.IOException;

import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.activemq.batch.model.Order;
import com.activemq.batch.module.config.ActiveMQConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Consumer {

	private static Logger log = LoggerFactory.getLogger(Consumer.class);

	@JmsListener(destination = ActiveMQConfig.VE2BATCH)
	public void receiveMessage(@Payload String order, @Headers MessageHeaders headers, Message message, Session session)
			throws JsonProcessingException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		Order readValue = mapper.readerFor(Order.class).readValue(order);
		readValue.setStatus("RECEIVED_FROM_VE");

		log.info("received <" + readValue + ">");

		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
		log.info("###### Message Details #####");
		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
		log.info("headers: " + headers);
		log.info("message: " + message);
		log.info("session: " + session);
		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
	}
}
