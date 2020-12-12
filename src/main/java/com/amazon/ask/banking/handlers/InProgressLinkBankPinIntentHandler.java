package com.amazon.ask.banking.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.banking.constants.GeneralConstants;
import com.amazon.ask.banking.constants.IntentNameConstants;
import com.amazon.ask.banking.constants.SlotNameConstants;
import com.amazon.ask.banking.constants.SpeechTextsConstants;
import com.amazon.ask.banking.model.AccountDetails;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler;
import com.amazon.ask.model.DialogState;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

public class InProgressLinkBankPinIntentHandler extends BaseHandler implements IntentRequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput, IntentRequest intentRequest) {
    	Slot routingNum = intentRequest.getIntent().getSlots().get(SlotNameConstants.ROUTING_NUMBER);
    	Slot accountNum = intentRequest.getIntent().getSlots().get(SlotNameConstants.ACCOUNT_NUMBER);
    	Slot category = intentRequest.getIntent().getSlots().get(SlotNameConstants.PAYMENT_CATEGORY);
    	Slot initiatePayment = intentRequest.getIntent().getSlots().get(SlotNameConstants.INITIATE_PAYMENT);
        return (handlerInput.matches(intentName(IntentNameConstants.CUSTOM_LINK_BANK_ACCOUNT_INTENT))
            && intentRequest.getDialogState() == DialogState.IN_PROGRESS)
        		&& routingNum.getValue() == null && accountNum.getValue() == null
        				&& category.getValue() == null && initiatePayment.getValue() == null;
    }

    @Override
	public Optional<Response> handle(HandlerInput input, IntentRequest intentRequest) {
		String token = input.getRequestEnvelope().getSession().getUser().getAccessToken();		
		AccountDetails result = BankClientLink.getAccountDetails(token);
		String speechText = null;
		String slotName = null;
		Intent intent = intentRequest.getIntent();
		Map<String, Slot> slotMap = intent.getSlots();
		Slot pin = slotMap.get(SlotNameConstants.PIN);
		if (pin.getValue().toString().equals(result.getPin())) {
			speechText = SpeechTextsConstants.PIN_SUCCESS_LINK_BANK_SPEECH_TEXT;
			slotName = SlotNameConstants.ROUTING_NUMBER;			
		} else {
			speechText = SpeechTextsConstants.PIN_INVALID_SPEECH_TEXT;	
			slotName = SlotNameConstants.PIN;			
		}		
		return buildResponse(input, speechText, GeneralConstants.LINK_BANK_CARD_TITLE,
				false, intentRequest, slotName);
	}
}