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

public class InProgressAccountBalanceIntentHandler extends BaseHandler implements IntentRequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput, IntentRequest intentRequest) {
    	Slot balance = intentRequest.getIntent().getSlots().get(SlotNameConstants.BALANCE);
        return (handlerInput.matches(intentName(IntentNameConstants.CUSTOM_ACCOUNT_BALANCE_INTENT))
            && intentRequest.getDialogState() == DialogState.IN_PROGRESS)
        		&& balance.getValue() == null;
    }

    @Override
	public Optional<Response> handle(HandlerInput input, IntentRequest intentRequest) {
		String token = input.getRequestEnvelope().getSession().getUser().getAccessToken();
		AccountDetails result = new AccountDetails();
		result.setAcctId("1234");
		result.setAcctType("Sample");
		result.setBalance("12");
		result.setPin("1234");
		result.setName("Karthick");
		//AccountDetails result = BankClientLink.getAccountDetails(token);
		String speechText = null;
		String slotName = null;
		Intent intent = intentRequest.getIntent();
		Map<String, Slot> slotMap = intent.getSlots();
		Slot pin = slotMap.get(SlotNameConstants.PIN);
		if (pin.getValue().toString().equals(result.getPin())) {
			speechText = SpeechTextsConstants.PIN_SUCCESS_BALANCE_SPEECH_TEXT + result.getAcctId();
			slotName = SlotNameConstants.BALANCE;			
		} else {
			speechText = SpeechTextsConstants.PIN_INVALID_SPEECH_TEXT;
			slotName = SlotNameConstants.PIN;			
		}		
		
		return buildResponse(input, speechText, GeneralConstants.ACCOUNT_BALANCE_CARD_TITLE,
				false, intentRequest, slotName);
	}
}