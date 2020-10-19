package com.marlap.bean;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFilter;

public class SecurityBeanQuestion {
	private String question;
	private String answer;
	
	
	private Timestamp createdOn;
	private String uniqueId;

	SecurityBeanQuestion() {
	}

	public SecurityBeanQuestion(String question) {
		this.question = question;
		
		this.createdOn = new Timestamp(System.currentTimeMillis());
		this.uniqueId = java.util.UUID.randomUUID().toString();
		
	}
	public SecurityBeanQuestion(String question,String answer) {
		this.question = question;
		this.answer = answer;
		this.createdOn = new Timestamp(System.currentTimeMillis());
		this.uniqueId = java.util.UUID.randomUUID().toString();
		
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	
	

	
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}


	

}