package com.activemq.batch.sender;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.activemq.batch.model.Order;
import com.activemq.batch.module.config.ActiveMQConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrderSender {

	private static Logger log = org.slf4j.LoggerFactory.getLogger(OrderSender.class);

	@Autowired
	private JmsTemplate jmsTemplate;

	public void send(Order myMessage) throws JsonProcessingException {
		log.info("sending with convertAndSend() to queue <" + myMessage + ">");

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(myMessage);

		jmsTemplate.convertAndSend(ActiveMQConfig.BATCH2VE, json);
	}
}
