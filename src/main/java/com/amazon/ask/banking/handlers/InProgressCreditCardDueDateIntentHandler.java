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

public class InProgressCreditCardDueDateIntentHandler extends BaseHandler implements IntentRequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput, IntentRequest intentRequest) {
    	Slot dueDate = intentRequest.getIntent().getSlots().get(SlotNameConstants.DUEDATE);
        return (handlerInput.matches(intentName(IntentNameConstants.CUSTOM_CREDIT_CARD_INTENT))
            && intentRequest.getDialogState() == DialogState.IN_PROGRESS)
        		&& dueDate.getValue() == null;
    }

    @Override
	public Optional<Response> handle(HandlerInput input, IntentRequest intentRequest) {
		String token = input.getRequestEnvelope().getSession().getUser().getAccessToken();
		AccountDetails result = BankClientLink.getAccountDetails(token);
		String speechText = null;
		Intent intent = intentRequest.getIntent();
		Map<String, Slot> slotMap = intent.getSlots();
		Slot pin = slotMap.get(SlotNameConstants.PIN);
		if (pin.getValue().toString().equals(result.getPin())) {
			speechText = SpeechTextsConstants.PIN_SUCCESS_CREDIT_SPEECH_TEXT;
			return input.getResponseBuilder()
					.withSpeech(speechText)
					.withReprompt(speechText)
					.addElicitSlotDirective(SlotNameConstants.DUEDATE, intentRequest.getIntent())
					.withSimpleCard(GeneralConstants.CREDIT_CARD_TITLE, speechText)
					.withShouldEndSession(false)
					.build();
		} else {
			speechText = SpeechTextsConstants.PIN_INVALID_SPEECH_TEXT;
			return input.getResponseBuilder()
					.withSpeech(speechText)
					.withReprompt(speechText)
					.addElicitSlotDirective(SlotNameConstants.PIN, intentRequest.getIntent())
					.withSimpleCard(GeneralConstants.CREDIT_CARD_TITLE, speechText)
					.withShouldEndSession(false)
					.build();
		}		

	}
}