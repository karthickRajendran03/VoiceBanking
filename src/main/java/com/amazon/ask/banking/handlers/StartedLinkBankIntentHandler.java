package com.amazon.ask.banking.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.banking.constants.IntentNameConstants;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler;
import com.amazon.ask.model.DialogState;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;

public class StartedLinkBankIntentHandler extends BaseHandler implements IntentRequestHandler {
		
    @Override
    public boolean canHandle(HandlerInput input, IntentRequest intentRequest) {
        return (input.matches(intentName(IntentNameConstants.CUSTOM_LINK_BANK_ACCOUNT_INTENT))
        		&& intentRequest.getDialogState() != DialogState.COMPLETED);
    }

    @Override
	public Optional<Response> handle(HandlerInput input, IntentRequest intentRequest) {
		Intent intent = intentRequest.getIntent();
		return input.getResponseBuilder().addDelegateDirective(intent).build();
	}

}
