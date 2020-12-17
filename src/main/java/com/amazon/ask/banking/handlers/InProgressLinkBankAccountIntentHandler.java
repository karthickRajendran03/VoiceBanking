package com.amazon.ask.banking.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.banking.constants.GeneralConstants;
import com.amazon.ask.banking.constants.IntentNameConstants;
import com.amazon.ask.banking.constants.SlotNameConstants;
import com.amazon.ask.banking.constants.SpeechTextsConstants;
import com.amazon.ask.banking.model.AccountDetails;
import com.amazon.ask.banking.model.AccountValidation;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler;
import com.amazon.ask.model.DialogState;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

public class InProgressLinkBankAccountIntentHandler extends BaseHandler implements IntentRequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput, IntentRequest intentRequest) {
    	Slot routingNum = intentRequest.getIntent().getSlots().get(SlotNameConstants.ROUTING_NUMBER);
    	Slot accountNum = intentRequest.getIntent().getSlots().get(SlotNameConstants.ACCOUNT_NUMBER);
    	Slot category = intentRequest.getIntent().getSlots().get(SlotNameConstants.PAYMENT_CATEGORY);
    	Slot initiatePayment = intentRequest.getIntent().getSlots().get(SlotNameConstants.INITIATE_PAYMENT);
        return (handlerInput.matches(intentName(IntentNameConstants.CUSTOM_LINK_BANK_ACCOUNT_INTENT))
            && intentRequest.getDialogState() == DialogState.IN_PROGRESS)
        		&& routingNum.getValue() != null && accountNum.getValue() != null
        			&& category.getValue() == null && initiatePayment.getValue() == null;
    }

    @Override
	public Optional<Response> handle(HandlerInput input, IntentRequest intentRequest) {			
		String speechText = null;
		String slotName = null;		
		Slot accountNum = intentRequest.getIntent().getSlots().get(SlotNameConstants.ACCOUNT_NUMBER);
		boolean endSesssion;
		String token = input.getRequestEnvelope().getSession().getUser().getAccessToken();	
		if(accountNum.getValue() != null) {
			AccountValidation mbaValidation = BankClientLink.getMbaasAccountValidation(token, accountNum.getValue());
			AccountValidation pbaValidation = BankClientLink.getPbaAccountValidation(token, accountNum.getValue());	
			if(null != mbaValidation && mbaValidation.getValid().equals("valid")
					&& null != pbaValidation && pbaValidation.getValid().equals("valid")) {							
				slotName = SlotNameConstants.PAYMENT_CATEGORY;
				speechText = SpeechTextsConstants.ACCOUNT_NUMBER_VALID;
				endSesssion = false;								
			} else {				
				slotName = SlotNameConstants.ACCOUNT_NUMBER;
				speechText = SpeechTextsConstants.ACCOUNT_NUMBER_INVALID;
				endSesssion = false;				
			}
		} else {
			speechText = SpeechTextsConstants.END_SESSION;
			endSesssion = true;			
		}		
		
		return buildResponse(input, speechText, GeneralConstants.LINK_BANK_CARD_TITLE,
				endSesssion, intentRequest, slotName);
							


	}
}