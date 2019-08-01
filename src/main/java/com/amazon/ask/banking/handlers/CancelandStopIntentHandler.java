package com.amazon.ask.banking.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.banking.constants.GeneralConstants;
import com.amazon.ask.banking.constants.IntentNameConstants;
import com.amazon.ask.banking.constants.SpeechTextsConstants;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;

public class CancelandStopIntentHandler extends BaseHandler {
	
    @Override
    public boolean canHandle(HandlerInput input) {
		return input.matches(intentName(IntentNameConstants.AMAZON_STOP_INTENT)
				.or(intentName(IntentNameConstants.AMAZON_CANCEL_INTENT)));
    }

	@Override
	public Optional<Response> handle(HandlerInput input) {
		return input.getResponseBuilder()
				.withSpeech(SpeechTextsConstants.STOP_SPEECH_TEXT)
				.withShouldEndSession(true)
				.withSimpleCard(GeneralConstants.STOP_CANCEL_CARD_TITLE, SpeechTextsConstants.STOP_SPEECH_CARD_TEXT)
				.withReprompt(SpeechTextsConstants.STOP_SPEECH_TEXT)
				.build();
	}
	
	boolean validSessionNeededForRepeat() {
		return false;
	}
}
