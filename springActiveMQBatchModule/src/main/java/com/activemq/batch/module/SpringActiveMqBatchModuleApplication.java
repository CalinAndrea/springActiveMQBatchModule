package com.activemq.batch.module;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.activemq.batch.model.Order;
import com.activemq.batch.sender.OrderSender;

@SpringBootApplication
@ComponentScan(basePackages = { "com.activemq" })
public class SpringActiveMqBatchModuleApplication implements ApplicationRunner {

	@Autowired
	private OrderSender orderSender;

	public static void main(String[] args) {

		SpringApplication.run(SpringActiveMqBatchModuleApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments arg0) throws Exception {

		for (int i = 0; i < 5; i++) {
			Order myMessage = new Order(i + " - Sending JMS Message using Embedded activeMQ", new Date(), "CREATED");
			orderSender.send(myMessage);
		}

		TimeUnit.HOURS.sleep(3);
	}
}
