package com.marlap.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.CronTask;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marlap.bean.SecurityBeanQuestion;
import com.marlap.service.SecurityService;
import com.marlap.util.Constant;
import com.marlap.util.Utility;


@RestController
@RequestMapping("${app.rest.base.path}/security")
public class SecurityResource extends ResponseBuilder {

	@Autowired
	private SecurityService service;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private Utility util;
	
	private Map<String, SecurityBeanQuestion> referenceMap = new HashMap<String, SecurityBeanQuestion>();
	
	@PostMapping(value = "/question",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> question(@RequestBody SecurityBeanQuestion requestString) {
		Optional<SecurityBeanQuestion> secBean= Optional.of(requestString);
		if(!secBean.isPresent()) {
			return buildResponseEntity(new SecurityBeanQuestion(environment.getProperty("app.marlab.wrong"),""), HttpStatus.BAD_REQUEST);
		}
		HttpSession httpSession= request.getSession();
		
		
		SecurityBeanQuestion secBeanResp = new SecurityBeanQuestion(environment.getProperty("app.marlab.questionResponse")+ service.requestQuestion(),"");
		referenceMap.put(secBeanResp.getUniqueId(), secBeanResp);
		httpSession.setAttribute(Constant.KEY, referenceMap);
				return buildResponseEntity(secBeanResp, HttpStatus.OK);
	}
	
	@PostMapping(value = "/answer",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> answer(@RequestBody SecurityBeanQuestion answerString) {
		Optional<SecurityBeanQuestion> secBean= Optional.of(answerString);
		if(!secBean.isPresent()) {
			return buildResponseEntity(environment.getProperty("app.marlab.wrong"), HttpStatus.BAD_REQUEST);
		}
		HttpSession httpSession= request.getSession();
		Map<String, SecurityBeanQuestion> referenceMap = new HashMap<String, SecurityBeanQuestion>();
		
	
		referenceMap = (HashMap<String, SecurityBeanQuestion>)httpSession.getAttribute(Constant.KEY);
		if(null!=referenceMap && util.checkQuestionIsExist(referenceMap, answerString)) {
			return buildResponseEntity(new SecurityBeanQuestion(answerString.getQuestion(),environment.getProperty("app.marlab.questionExist") ),
					HttpStatus.BAD_REQUEST );
			
		}
		
		if(answerString.getQuestion().contains(Constant.ANSWER)) {
			String numList = util.extractInt(answerString.getQuestion());
			
			String numberlist =  numList.substring(0,numList.lastIndexOf(","));
					
			Integer sumQues = service.getSumForQuestion(numberlist);
			String [] processed = numList.split(",");
			Integer sumAnswer = Integer.parseInt(processed[processed.length-1]);
			
			if(!sumQues.equals(sumAnswer)) {
				return buildResponseEntity(environment.getProperty("app.marlab.wrong"), HttpStatus.BAD_REQUEST);
			} else {
				return buildResponseEntity(environment.getProperty("app.marlab.greatans"), HttpStatus.BAD_REQUEST);
			}
		
			
		}
		
		Integer sum = service.getSumForQuestion(answerString.getQuestion());
		return buildResponseEntity(new SecurityBeanQuestion(answerString.getQuestion(),environment.getProperty("app.marlab.great")+answerString.getQuestion()+environment.getProperty("app.marlab.answer")+" "+sum ),
				HttpStatus.OK );
		
		
		
		
	}
	
	@Async
	@Scheduled(fixedRate=900000)
	public void cleanUp() throws InterruptedException  {
		
		long now = System.currentTimeMillis();
		List<String> keys = referenceMap.entrySet().stream()
				.filter(entry -> (now - entry.getValue().getCreatedOn().getTime()) / (24 * 60 * 60 * 1000) > 2)
				.map(Entry::getKey).collect(Collectors.toList());
		keys.forEach(key -> referenceMap.remove(key));
		HttpSession httpSession= request.getSession();
		httpSession.removeAttribute(Constant.KEY);
	
		
	}

}
