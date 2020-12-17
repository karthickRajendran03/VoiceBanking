package com.amazon.ask.banking.constants;

public class SpeechTextsConstants {
	
	public static final String WELCOME_SPEECH_TEXT = "Welcome to Discover Voice banking. You can ask me to link bank account for all payments, check account balance, credit card details, Personal loan details. What would you like to do? Please say Repeat to hear it again";
	public static final String ACCOUNT_LINKING_SPEECH_TEXT = "Please link your account first in Alexa App to proceed. Goodbye";
	public static final String HELP_SPEECH_TEXT =  "<voice name=\"Brian\">You can do request for linking bank account, account balance, credit card details,Personal loan details. What would you like to do? Please say Repeat to hear it again</voice>";
	public static final String HELP_SPEECH_CARD_TEXT =  "You can do account balance, credit card bill payment. What would you like to do?";
	public static final String STOP_SPEECH_TEXT = "<emphasis level=\"strong\">Goodbye</emphasis>";
	public static final String STOP_SPEECH_CARD_TEXT = "Goodbye";
	public static final String FALLBACK_SPEECH_TEXT = "Sorry, I don't know what you have asked. You can say try saying help!";
	public static final String NO_REPEAT_SPEECH_TEXT = "I am Sorry, There is nothing to repeat at this time";
	public static final String PIN_SUCCESS_BALANCE_SPEECH_TEXT = "Your Voice PIN is verified. Did you want to know about your savings account ending with ";
	public static final String PIN_SUCCESS_FUNDS_SPEECH_TEXT = "Your Voice PIN is verified. You are transferring to Varmas account number ending with 7896. Do you want to transfer from savings account ending with 0123";
	public static final String PIN_SUCCESS_CREDIT_SPEECH_TEXT = "Your Voice PIN is verified. Do you want to know the credit card details";
	public static final String PIN_SUCCESS_PERSONAL_LOAN_SPEECH_TEXT = "Your Voice PIN is verified. Do you want to know the personal loan details";
	public static final String PIN_INVALID_SPEECH_TEXT = "Your PIN is invalid. Please provide the valid Voice PIN.";
	public static final String PIN_INVALID_LINK_BANK_SPEECH_TEXT = "Your OTP is invalid. Please provide the valid OTP received in your registered mobile number";
	public static final String INVALID_REQUEST_SPEECH_TEXT = "Your request was invalid, You can say try saying help!";	
	public static final String UNHANDLED_EXCEPTION_SPEECH_TEXT = "An Error Occured while handling your mail request. Please try again later.";	
	public static final String PAUSE = "<break time=\"2s\"/>";
	public static final String PAUSE1 = "<break time=\"1s\"/>";
	public static final String END_SESSION = "Thank you for using Discover Voice Banking. Goodbye";
	public static final String END_SESSION_VALIDATION = "Please contact customer care executive for more details. Thank you for using Discover Voice Banking. Goodbye";
	public static final String CREDIT_CARD_DUEDATE = "Your Credit card due date is December 20 2020 and has hundred dollar outstanding amount, Do you want to pay the credit card bill payment now?";
	public static final String CREDIT_CARD_LINK_BANK = "No bank account was linked to initiate payment. Please say link bank account to proceed";
	public static final String PERSONAL_LOAN_DUEDATE = "Your Personal Loan payment due date is December 20 2020 and has emi of hundred dollar, Do you want to initiate loan payment now?";
	public static final String PERSONAL_LOAN_EXISTING_ACCOUNT = "You have one linked bank account ending with 2334. Do you want to use this account to initiate loan payment";
	public static final String PERSONAL_LOAN_LINK_BANK = "Please say link bank account to proceed";	
	public static final String CREDIT_CARD_PAYMENT = "Your credit card payment is done successfully. Please note down the transaction number: 121313 for future reference. Thank you for using Discover Voice Banking. Goodbye";
	public static final String PIN_SUCCESS_LINK_BANK_SPEECH_TEXT = "Your OTP is verified. Please provide the Routing Number for verification";
	public static final String ROUTING_NUMBER_VALID = "Routing Number is validated, Please provide your Account Number";
	public static final String ROUTING_NUMBER_INVALID = "Routing Number is invalid, Please provide the valid routing number";
	public static final String ACCOUNT_NUMBER_VALID = "Your Account Number is validated successfully and linked the requested bank account, Please select anyone of the following to initiate payment   " +PAUSE1+ " credit card" +PAUSE1+  " student loan "+PAUSE1+" personal loan ";
	public static final String ACCOUNT_NUMBER_INVALID = "Sorry the account number you have provided failed in our validation. Please provide the valid account number to proceed";
	public static final String CREDIT_CARD_PROCESS = "You have one credit card account ending with ";
	public static final String CREDIT_CARD_PROCESS_1 = " having due date of December 20 2020, Do you want to proceed with the  payment";
	public static final String PERSONAL_LOAN_PROCESS = "You have personal loan having due date of December 20 2020, Do you want to proceed with the payment";
	public static final String STUDENT_LOAN_PROCESS = "You have student loan with due date of December 20 2020, Do you want to proceed with the payment";
	public static final String PAYMENT_PROCESSED = "Your payment was initiated successfully. Please note down the transaction number: 121313 for future reference. Thank you for using Discover Voice Banking. Goodbye";	
}
