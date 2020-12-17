package com.amazon.ask.banking.handlers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.amazon.ask.banking.model.AccountDetails;
import com.amazon.ask.banking.model.AccountValidation;
import com.amazon.ask.banking.model.CreditCardDetails;
import com.amazon.ask.banking.model.RoutingNumberValidation;

public class BankClientLink {
	static final String API_URL = "https://bankdiscoverapi.tech/auth/rest/bank/getBalance";
	static final String CREDIT_CARD_API = "https://bankdiscoverapi.tech/auth/rest/bank/getCreditCardDetails";
	static final String ABA_VALIDATION = "https://bankdiscoverapi.tech/auth/rest/bank/abaValidation/";
	static final String MBAAS_VALIDATION = "https://bankdiscoverapi.tech/auth/rest/bank/mbaasValidation/";
	static final String PBA_VALIDATION = "https://bankdiscoverapi.tech/auth/rest/bank/pbaValidation/";
	
	public static AccountDetails getAccountDetails(String token) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		System.out.println("Token :->" + token);
		headers.add("Authorization", "Bearer " +token);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<AccountDetails> acctDetails = restTemplate.exchange(API_URL, HttpMethod.GET, entity, AccountDetails.class);
		return acctDetails.getBody();
	}
	
	public static CreditCardDetails getCreditCardDetails(String token) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		System.out.println("Token :->" + token);
		headers.add("Authorization", "Bearer " +token);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<CreditCardDetails> creditCardDetails = restTemplate.exchange(CREDIT_CARD_API, HttpMethod.GET, entity, CreditCardDetails.class);
		return creditCardDetails.getBody();
	}
	
	public static RoutingNumberValidation getRoutingValidation(String token, String routingNumber) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		System.out.println("Token :->" + token);
		headers.add("Authorization", "Bearer " +token);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<RoutingNumberValidation> abaValidation = restTemplate.exchange(ABA_VALIDATION+routingNumber, HttpMethod.GET, entity, RoutingNumberValidation.class);
		return abaValidation.getBody();
	}
	
	public static AccountValidation getMbaasAccountValidation(String token, String accountNumber) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		System.out.println("Token :->" + token);
		headers.add("Authorization", "Bearer " +token);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<AccountValidation> abaValidation = restTemplate.exchange(MBAAS_VALIDATION+accountNumber, HttpMethod.GET, entity, AccountValidation.class);
		return abaValidation.getBody();
	}
	
	public static AccountValidation getPbaAccountValidation(String token, String accountNumber) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		System.out.println("Token :->" + token);
		headers.add("Authorization", "Bearer " +token);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<AccountValidation> abaValidation = restTemplate.exchange(PBA_VALIDATION+accountNumber, HttpMethod.GET, entity, AccountValidation.class);
		return abaValidation.getBody();
	}
	
}
