package com.amazon.ask.banking.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.banking.constants.GeneralConstants;
import com.amazon.ask.banking.constants.IntentNameConstants;
import com.amazon.ask.banking.constants.SlotNameConstants;
import com.amazon.ask.banking.constants.SpeechTextsConstants;
import com.amazon.ask.banking.model.AccountDetails;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler;
import com.amazon.ask.model.DialogState;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

public class InProgressLinkBankCategoryIntentHandler extends BaseHandler implements IntentRequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput, IntentRequest intentRequest) {
    	Slot routingNum = intentRequest.getIntent().getSlots().get(SlotNameConstants.ROUTING_NUMBER);
    	Slot accountNum = intentRequest.getIntent().getSlots().get(SlotNameConstants.ACCOUNT_NUMBER);
    	Slot category = intentRequest.getIntent().getSlots().get(SlotNameConstants.PAYMENT_CATEGORY);
    	Slot initiatePayment = intentRequest.getIntent().getSlots().get(SlotNameConstants.INITIATE_PAYMENT);
        return (handlerInput.matches(intentName(IntentNameConstants.CUSTOM_LINK_BANK_ACCOUNT_INTENT))
            && intentRequest.getDialogState() == DialogState.IN_PROGRESS)
        		&& routingNum.getValue() != null && accountNum.getValue() != null && category.getValue() != null
        		&& initiatePayment.getValue() == null;
    }
    
    @Override
	public Optional<Response> handle(HandlerInput input, IntentRequest intentRequest) {			
		String speechText = null;
		String slotName = null;
		Slot category = intentRequest.getIntent().getSlots().get(SlotNameConstants.PAYMENT_CATEGORY);
		boolean endSesssion;
		String token = input.getRequestEnvelope().getSession().getUser().getAccessToken();		
		//AccountDetails result = BankClientLink.getABAService(token, routingNum.getValue());
		if(category.getValue() != null) {
			endSesssion = false;
			slotName = SlotNameConstants.INITIATE_PAYMENT;
			if(category.getValue().equals(GeneralConstants.CREDIT_CARD_CATEGORY)) {
				speechText = SpeechTextsConstants.CREDIT_CARD_PROCESS;			
			} else if(category.getValue().equals(GeneralConstants.PERSONAL_LOAN_CATEGORY)) {
				speechText = SpeechTextsConstants.PERSONAL_LOAN_PROCESS;
			} else if(category.getValue().equals(GeneralConstants.STUDENT_LOAN_CATEGORY)) {
				speechText = SpeechTextsConstants.STUDENT_LOAN_PROCESS;
			}			
		} else {
			endSesssion = true;
			speechText = SpeechTextsConstants.END_SESSION;			
		}				

		return buildResponse(input, speechText, GeneralConstants.LINK_BANK_CARD_TITLE,
				endSesssion, intentRequest, slotName);


	}
}