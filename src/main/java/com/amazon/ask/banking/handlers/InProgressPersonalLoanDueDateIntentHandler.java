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

public class InProgressPersonalLoanDueDateIntentHandler extends BaseHandler implements IntentRequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput, IntentRequest intentRequest) {
    	Slot dueDate = intentRequest.getIntent().getSlots().get(SlotNameConstants.DUEDATE);
    	Slot payment = intentRequest.getIntent().getSlots().get(SlotNameConstants.PAYMENT);
    	Slot initiatePayment = intentRequest.getIntent().getSlots().get(SlotNameConstants.INITIATE_PAYMENT);
        return (handlerInput.matches(intentName(IntentNameConstants.CUSTOM_PERSONAL_LOAN_INTENT))
            && intentRequest.getDialogState() == DialogState.IN_PROGRESS)
        		&& dueDate.getValue() != null && payment.getValue() == null && initiatePayment.getValue() == null;
    }

    @Override
	public Optional<Response> handle(HandlerInput input, IntentRequest intentRequest) {			
		String speechText = null;
		String slotName = null;
		Slot dueDateValue = intentRequest.getIntent().getSlots().get(SlotNameConstants.DUEDATE);
		boolean endSesssion;		
		if(dueDateValue.getValue() != null && dueDateValue.getResolutions().getResolutionsPerAuthority().get(0).getStatus().getCode().toString().equals(GeneralConstants.SUCCESS_MATCH)) {
			speechText = SpeechTextsConstants.PERSONAL_LOAN_DUEDATE;
			endSesssion = false;
			slotName = SlotNameConstants.PAYMENT;
		} else {
			speechText = SpeechTextsConstants.END_SESSION;
			endSesssion = true;
		}		
							
		return buildResponse(input, speechText, GeneralConstants.PERSONAL_LOAN_CARD_TITLE,
				endSesssion, intentRequest, slotName);
	}
}