package com.amazon.ask.banking.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.banking.constants.GeneralConstants;
import com.amazon.ask.banking.constants.IntentNameConstants;
import com.amazon.ask.banking.constants.SpeechTextsConstants;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;

public class HelpIntentHandler extends BaseHandler {
	
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName(IntentNameConstants.AMAZON_HELP_INTENT));
    }

    @Override
	public Optional<Response> handle(HandlerInput input) {
		String speechText = SpeechTextsConstants.HELP_SPEECH_TEXT;
		return input.getResponseBuilder()
				.withSpeech(speechText)
				.withShouldEndSession(false)
				.withSimpleCard(GeneralConstants.HELP_CARD_TITLE, SpeechTextsConstants.HELP_SPEECH_CARD_TEXT)
				.withReprompt(speechText)
				.build();
	}
}
