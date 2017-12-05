package com.activemq.batch.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	private String content;
	private Date timestamp;
	private String status;

	public Order() {

	}

	public Order(String content, Date timestamp, String status) {
		this.content = content;
		this.timestamp = timestamp;
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Order{" + "content='" + content + '\'' + ", timestamp=" + timestamp + "Status: " + status + '}';
	}

}