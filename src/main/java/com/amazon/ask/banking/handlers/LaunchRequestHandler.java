package com.amazon.ask.banking.handlers;

import static com.amazon.ask.request.Predicates.requestType;

import java.util.Optional;

import com.amazon.ask.banking.constants.GeneralConstants;
import com.amazon.ask.banking.constants.SpeechTextsConstants;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;

public class LaunchRequestHandler extends BaseHandler{
	
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
    	String speechText = null;
    	boolean endSesssion;
		/*if (input.getRequestEnvelope().getSession().getUser().getAccessToken() == null) {
			speechText = SpeechTextsConstants.ACCOUNT_LINKING_SPEECH_TEXT;
			endSesssion = true;
			return input.getResponseBuilder()
					.withSpeech(speechText)
					.withLinkAccountCard()
					.withShouldEndSession(endSesssion)
					.build();
		} else {
			speechText = SpeechTextsConstants.WELCOME_SPEECH_TEXT;
			endSesssion = false;
			return buildResponse(input, speechText, GeneralConstants.WELCOME_TITLE, endSesssion);
		} */ 
    	speechText = SpeechTextsConstants.WELCOME_SPEECH_TEXT;
		endSesssion = false;
		return buildResponse(input, speechText, GeneralConstants.WELCOME_TITLE, endSesssion, true);
    }

}
