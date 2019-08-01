package com.amazon.ask.banking.constants;

public class SpeechTextsConstants {
	
	public static final String WELCOME_SPEECH_TEXT = "Welcome to Voice banking. You can ask me questions related to account balance, credit card details. What would you like to do?";
	public static final String ACCOUNT_LINKING_SPEECH_TEXT = "Please link your account first in Alexa App to proceed. Goodbye";
	public static final String HELP_SPEECH_TEXT =  "<voice name=\"Brian\">You can do account balance, credit card bill payment. What would you like to do?</voice>";
	public static final String HELP_SPEECH_CARD_TEXT =  "You can do account balance, credit card bill payment. What would you like to do?";
	public static final String STOP_SPEECH_TEXT = "<emphasis level=\"strong\">Goodbye</emphasis>";
	public static final String STOP_SPEECH_CARD_TEXT = "Goodbye";
	public static final String FALLBACK_SPEECH_TEXT = "Sorry, I don't know what you have asked. You can say try saying help!";
	public static final String NO_REPEAT_SPEECH_TEXT = "I am Sorry, There is nothing to repeat at this time";
	public static final String PIN_SUCCESS_BALANCE_SPEECH_TEXT = "Your Voice PIN is verified. Did you want to know about your savings account ending with ";
	public static final String PIN_SUCCESS_FUNDS_SPEECH_TEXT = "Your Voice PIN is verified. You are transferring to Varmas account number ending with 7896. Do you want to transfer from savings account ending with 0123";
	public static final String PIN_SUCCESS_CREDIT_SPEECH_TEXT = "Your Voice PIN is verified. Do you want to know the credit card due date";
	public static final String PIN_INVALID_SPEECH_TEXT = "Your PIN is invalid. Please provide the valid Voice PIN.";
	public static final String INVALID_REQUEST_SPEECH_TEXT = "Your request was invalid, You can say try saying help!";	
	public static final String UNHANDLED_EXCEPTION_SPEECH_TEXT = "An Error Occured while handling your mail request. Please try again later.";	
	public static final String PAUSE = "<break time=\"2s\"/>";
	public static final String END_SESSION = "Thank you for using Voice Banking. Goodbye";
	public static final String CREDIT_CARD_DUEDATE = "Your Credit card due date is August 10 2019, Do you want to pay credit card bill from account ending with ";
	public static final String CREDIT_CARD_PAYMENT = "Your credit card payment is done successfully. Please note down the transaction number: 121313 for future reference. Thank you for using Voice Banking. Goodbye";
}
