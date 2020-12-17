package com.amazon.ask.banking.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.banking.constants.GeneralConstants;
import com.amazon.ask.banking.constants.IntentNameConstants;
import com.amazon.ask.banking.constants.SlotNameConstants;
import com.amazon.ask.banking.constants.SpeechTextsConstants;
import com.amazon.ask.banking.model.RoutingNumberValidation;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler;
import com.amazon.ask.model.DialogState;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

public class InProgressLinkBankRoutingIntentHandler extends BaseHandler implements IntentRequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput, IntentRequest intentRequest) {
    	Slot routingNum = intentRequest.getIntent().getSlots().get(SlotNameConstants.ROUTING_NUMBER);
    	Slot accountNum = intentRequest.getIntent().getSlots().get(SlotNameConstants.ACCOUNT_NUMBER);
    	Slot category = intentRequest.getIntent().getSlots().get(SlotNameConstants.PAYMENT_CATEGORY);
    	Slot initiatePayment = intentRequest.getIntent().getSlots().get(SlotNameConstants.INITIATE_PAYMENT);
        return (handlerInput.matches(intentName(IntentNameConstants.CUSTOM_LINK_BANK_ACCOUNT_INTENT))
            && intentRequest.getDialogState() == DialogState.IN_PROGRESS)
        		&& routingNum.getValue() != null && accountNum.getValue() == null
        			&& category.getValue() == null && initiatePayment.getValue() == null;
    }

    @Override
	public Optional<Response> handle(HandlerInput input, IntentRequest intentRequest) {			
		String speechText = null;
		String slotName = null;
		Slot routingNum = intentRequest.getIntent().getSlots().get(SlotNameConstants.ROUTING_NUMBER);
		boolean endSesssion;
		String token = input.getRequestEnvelope().getSession().getUser().getAccessToken();				
		if(routingNum.getValue() != null) {
			RoutingNumberValidation result = BankClientLink.getRoutingValidation(token, routingNum.getValue());								
			if(null != result && result.getValid().equals("valid")) {	
				speechText = SpeechTextsConstants.ROUTING_NUMBER_VALID;
				slotName = SlotNameConstants.ACCOUNT_NUMBER;
				endSesssion = false;				
			} else {
				speechText = SpeechTextsConstants.ROUTING_NUMBER_INVALID;
				slotName = SlotNameConstants.ROUTING_NUMBER;
				endSesssion = false;				
			}
		} else {
			speechText = SpeechTextsConstants.END_SESSION_VALIDATION;
			endSesssion = true;			
		}		
		
		return buildResponse(input, speechText, GeneralConstants.LINK_BANK_CARD_TITLE,
				endSesssion, intentRequest, slotName);
	}
}