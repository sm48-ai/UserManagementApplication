package com.satish.servicesIMPL;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.satish.dto.QuoteApiResponseDTO;
import com.satish.services.DashboradService;

@Service
public class DashboardServiceIMPL implements DashboradService{
    
	private String quoteApiURL="https://dummyjson.com/quotes/random";
	
	@Override
	public QuoteApiResponseDTO getQuote() {
		
		RestTemplate rt=new RestTemplate();
		ResponseEntity<QuoteApiResponseDTO> forEntity=
				rt.getForEntity(quoteApiURL, QuoteApiResponseDTO.class);
		
		QuoteApiResponseDTO body=forEntity.getBody();
				
		return body;
	}

}
