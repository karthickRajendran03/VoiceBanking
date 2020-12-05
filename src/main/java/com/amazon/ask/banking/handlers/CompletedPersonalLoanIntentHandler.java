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

public class CompletedPersonalLoanIntentHandler extends BaseHandler implements IntentRequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput, IntentRequest intentRequest) {
        return (handlerInput.matches(intentName(IntentNameConstants.CUSTOM_PERSONAL_LOAN_INTENT))
            && intentRequest.getDialogState() == DialogState.COMPLETED);
    }

    @Override
    public Optional<Response> handle(HandlerInput input, IntentRequest intentRequest) {		
		Slot initiatePayment = intentRequest.getIntent().getSlots().get(SlotNameConstants.INITIATE_PAYMENT);
		String speechText;		
		boolean endSession;
		if(initiatePayment.getValue() != null && initiatePayment.getResolutions().getResolutionsPerAuthority().get(0).getStatus().getCode().toString().equals(GeneralConstants.SUCCESS_MATCH)) {
			speechText = SpeechTextsConstants.PAYMENT_PROCESSED;
			endSession = true;
		} else {
			speechText = SpeechTextsConstants.PERSONAL_LOAN_LINK_BANK;	
			endSession = false;
		}		
		return buildResponse(input, speechText, GeneralConstants.PERSONAL_LOAN_CARD_TITLE, endSession, false);
	       
    }
    
    boolean validSessionNeededForRepeat() {
		return false;
	}
}