package com.amazon.ask.banking.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.banking.constants.GeneralConstants;
import com.amazon.ask.banking.constants.IntentNameConstants;
import com.amazon.ask.banking.constants.SpeechTextsConstants;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;

public class FallbackIntentHandler extends BaseHandler{	

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName(IntentNameConstants.AMAZON_FALLBACK_INTENT));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {                
		return buildResponse(input, SpeechTextsConstants.FALLBACK_SPEECH_TEXT, GeneralConstants.FALLBACK_CARD_TITLE,
				false, true);
    }

}
