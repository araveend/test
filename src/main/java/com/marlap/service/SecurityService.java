package com.marlap.service;

import javax.servlet.http.HttpSession;

public interface SecurityService {

	public String requestQuestion();

	public Integer getSumForQuestion(String ansString);
	
	public boolean validateOriginalQuestion(String question, String prevQuestion);

}
