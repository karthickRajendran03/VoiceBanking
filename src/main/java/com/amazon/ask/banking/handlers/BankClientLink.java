package com.amazon.ask.banking.handlers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.amazon.ask.banking.model.AccountDetails;

public class BankClientLink {
	static final String API_URL = "http://authserverrr.cfapps.io/auth/rest/hello/getBalance";
	
	public static AccountDetails getAccountDetails(String token) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " +token);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<AccountDetails> acctDetails = restTemplate.exchange(API_URL, HttpMethod.GET, entity, AccountDetails.class);
		return acctDetails.getBody();
	}
}
