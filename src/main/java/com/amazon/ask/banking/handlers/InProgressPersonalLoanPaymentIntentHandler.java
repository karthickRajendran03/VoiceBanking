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

public class InProgressPersonalLoanPaymentIntentHandler extends BaseHandler implements IntentRequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput, IntentRequest intentRequest) {
    	Slot dueDate = intentRequest.getIntent().getSlots().get(SlotNameConstants.DUEDATE);
    	Slot payment = intentRequest.getIntent().getSlots().get(SlotNameConstants.PAYMENT);
    	Slot initiatePayment = intentRequest.getIntent().getSlots().get(SlotNameConstants.INITIATE_PAYMENT);
        return (handlerInput.matches(intentName(IntentNameConstants.CUSTOM_PERSONAL_LOAN_INTENT))
            && intentRequest.getDialogState() == DialogState.IN_PROGRESS)
        		&& dueDate.getValue() != null && payment.getValue() != null
        		&& initiatePayment.getValue() == null;
    }

    @Override
	public Optional<Response> handle(HandlerInput input, IntentRequest intentRequest) {			
		String speechText = null;
		String slotName = null;
		Slot payment = intentRequest.getIntent().getSlots().get(SlotNameConstants.PAYMENT);
		boolean endSesssion;
		String token = input.getRequestEnvelope().getSession().getUser().getAccessToken();
		AccountDetails result = new AccountDetails();
		result.setAcctId("1234");
		result.setAcctType("Sample");
		result.setBalance("12");
		result.setPin("1234");
		result.setName("Karthick");
		//AccountDetails result = BankClientLink.getAccountDetails(token);
		if(payment.getValue() != null && payment.getResolutions().getResolutionsPerAuthority().get(0).getStatus().getCode().toString().equals(GeneralConstants.SUCCESS_MATCH)) {
			speechText = SpeechTextsConstants.PERSONAL_LOAN_EXISTING_ACCOUNT;
			endSesssion = false;
			slotName = SlotNameConstants.INITIATE_PAYMENT;
		} else {
			speechText = SpeechTextsConstants.END_SESSION;
			endSesssion = true;			
		}		
							
		return buildResponse(input, speechText, GeneralConstants.PERSONAL_LOAN_CARD_TITLE,
				endSesssion, intentRequest, slotName);
	}
}