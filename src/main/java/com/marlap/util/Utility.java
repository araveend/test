package com.marlap.util;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.marlap.bean.SecurityBeanQuestion;

@Component
public class Utility {

	public boolean checkQuestionIsExist(Map<String,SecurityBeanQuestion> referenceMap,SecurityBeanQuestion secBeanQuestion) {
		
		
		
		 for (Map.Entry<String, SecurityBeanQuestion> entry : referenceMap.entrySet()) {
			
			 if(entry.getValue().getQuestion().equals(secBeanQuestion.getQuestion()))
		       return Boolean.TRUE;
		    }
		
		 return Boolean.FALSE;
	}
	
	 public String extractInt(String str) 
     { 
         // Replacing every non-digit number 
         // with a space(" ") 
         str = str.replaceAll("[^\\d]", " "); 
   
         // Remove extra spaces from the beginning 
         // and the ending of the string 
         str = str.trim(); 
   
         // Replace all the consecutive white 
         // spaces with a single space 
         str = str.replaceAll(" +", " "); 
   
         if (str.equals("")) 
             return "-1"; 
   
         return str.replace(" ", ","); 
     } 

}
