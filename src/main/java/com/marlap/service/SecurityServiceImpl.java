package com.marlap.service;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.marlap.util.Constant;




@Service
public class SecurityServiceImpl implements SecurityService {

	

	@Override
	public String requestQuestion() {
		
		 Random random = new Random();
	        IntStream randomIntegerNumbers = random.ints(3, 2, 15);
	        return Constant.QUESTION_PREFIX + randomIntegerNumbers
	                .boxed()
	                .map(String::valueOf)
	                .collect(Collectors.joining(","));
		
	}

	
	@Override
	 public  Integer getSumForQuestion(String originalQuestion)  {
	
		 
		 
	       
	        return Stream.of(originalQuestion.trim().split(","))
	                .map(Integer::parseInt)
	                .reduce(0, Integer::sum);
	 }
	
	@Override
	public  boolean validateOriginalQuestion(String question, String prevQuestion) {
	        return prevQuestion != null ? prevQuestion.equals(question) : false;
	 }


   

   
	

}
