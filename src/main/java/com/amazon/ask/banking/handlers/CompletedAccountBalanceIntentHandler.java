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

public class CompletedAccountBalanceIntentHandler extends BaseHandler implements IntentRequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput, IntentRequest intentRequest) {
        return (handlerInput.matches(intentName(IntentNameConstants.CUSTOM_ACCOUNT_BALANCE_INTENT))
            && intentRequest.getDialogState() == DialogState.COMPLETED);
    }

    @Override
    public Optional<Response> handle(HandlerInput input, IntentRequest intentRequest) {
		String token = input.getRequestEnvelope().getSession().getUser().getAccessToken();
		AccountDetails result = new AccountDetails();
		result.setAcctId("1234");
		result.setAcctType("Sample");
		result.setBalance("12");
		result.setPin("1234");
		result.setName("Karthick");
		//AccountDetails result = BankClientLink.getAccountDetails(token);
		Slot verify = intentRequest.getIntent().getSlots().get(SlotNameConstants.BALANCE);
		String speechText;		
		if(verify.getValue() != null && verify.getResolutions().getResolutionsPerAuthority().get(0).getStatus().getCode().toString().equals(GeneralConstants.SUCCESS_MATCH)) {
			speechText = "Your " +result.getAcctType() + " account ending with account number "+result.getAcctId() +" has an account balance " +result.getBalance()+ " rupees. Thank you for using Voice Banking. Goodbye";			
		} else {
			speechText = SpeechTextsConstants.END_SESSION;			
		}		
		return buildResponse(input, speechText, GeneralConstants.ACCOUNT_BALANCE_CARD_TITLE, true, false);
    }
    
    boolean validSessionNeededForRepeat() {
		return false;
	}
}