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

public class InProgressCreditCardPaymentIntentHandler extends BaseHandler implements IntentRequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput, IntentRequest intentRequest) {
    	Slot payment = intentRequest.getIntent().getSlots().get(SlotNameConstants.PAYMENT);
        return (handlerInput.matches(intentName(IntentNameConstants.CUSTOM_CREDIT_CARD_INTENT))
            && intentRequest.getDialogState() == DialogState.IN_PROGRESS)
        		&& payment.getValue() == null;
    }

    @Override
	public Optional<Response> handle(HandlerInput input, IntentRequest intentRequest) {			
		String speechText = null;
		Slot dueDateValue = intentRequest.getIntent().getSlots().get(SlotNameConstants.DUEDATE);
		boolean endSesssion;
		String token = input.getRequestEnvelope().getSession().getUser().getAccessToken();
		AccountDetails result = BankClientLink.getAccountDetails(token);
		if(dueDateValue.getValue() != null && dueDateValue.getResolutions().getResolutionsPerAuthority().get(0).getStatus().getCode().toString().equals(GeneralConstants.SUCCESS_MATCH)) {
			speechText = SpeechTextsConstants.CREDIT_CARD_DUEDATE +result.getAcctId();
			endSesssion = false;
		} else {
			speechText = SpeechTextsConstants.END_SESSION;
			endSesssion = true;
		}		
							
		return input.getResponseBuilder()
				.withSpeech(speechText)
				.withReprompt(speechText)
				.addElicitSlotDirective(SlotNameConstants.PAYMENT, intentRequest.getIntent())
				.withShouldEndSession(endSesssion)
				.withSimpleCard(GeneralConstants.CREDIT_CARD_TITLE, speechText)
				.build();

	}
}