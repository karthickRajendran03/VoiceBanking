package com.amazon.ask.banking.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.banking.constants.GeneralConstants;
import com.amazon.ask.banking.constants.IntentNameConstants;
import com.amazon.ask.banking.constants.SlotNameConstants;
import com.amazon.ask.banking.constants.SpeechTextsConstants;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler;
import com.amazon.ask.model.DialogState;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

public class CompletedCreditCardIntentHandler extends BaseHandler implements IntentRequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput, IntentRequest intentRequest) {
        return (handlerInput.matches(intentName(IntentNameConstants.CUSTOM_CREDIT_CARD_INTENT))
            && intentRequest.getDialogState() == DialogState.COMPLETED);
    }

    @Override
    public Optional<Response> handle(HandlerInput input, IntentRequest intentRequest) {		
		Slot payment = intentRequest.getIntent().getSlots().get(SlotNameConstants.PAYMENT);
		String speechText;		
		if(payment.getValue() != null && payment.getResolutions().getResolutionsPerAuthority().get(0).getStatus().getCode().toString().equals(GeneralConstants.SUCCESS_MATCH)) {
			speechText = SpeechTextsConstants.CREDIT_CARD_PAYMENT;			
		} else {
			speechText = SpeechTextsConstants.END_SESSION;			
		}		
		return buildResponse(input, speechText, GeneralConstants.CREDIT_CARD_TITLE, true, false);
	       
    }
    
    boolean validSessionNeededForRepeat() {
		return false;
	}
}