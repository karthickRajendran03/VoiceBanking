package com.amazon.ask.banking;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import com.amazon.ask.banking.exception.ExceptionIntentHandler;
import com.amazon.ask.banking.handlers.CancelandStopIntentHandler;
import com.amazon.ask.banking.handlers.CompletedAccountBalanceIntentHandler;
import com.amazon.ask.banking.handlers.CompletedCreditCardIntentHandler;
import com.amazon.ask.banking.handlers.CompletedLinkBankIntentHandler;
import com.amazon.ask.banking.handlers.CompletedPersonalLoanIntentHandler;
import com.amazon.ask.banking.handlers.FallbackIntentHandler;
import com.amazon.ask.banking.handlers.HelpIntentHandler;
import com.amazon.ask.banking.handlers.InProgressAccountBalanceIntentHandler;
import com.amazon.ask.banking.handlers.InProgressCreditCardDueDateIntentHandler;
import com.amazon.ask.banking.handlers.InProgressCreditCardPaymentIntentHandler;
import com.amazon.ask.banking.handlers.InProgressCreditCardPinIntentHandler;
import com.amazon.ask.banking.handlers.InProgressLinkBankAccountIntentHandler;
import com.amazon.ask.banking.handlers.InProgressLinkBankCategoryIntentHandler;
import com.amazon.ask.banking.handlers.InProgressLinkBankPinIntentHandler;
import com.amazon.ask.banking.handlers.InProgressLinkBankRoutingIntentHandler;
import com.amazon.ask.banking.handlers.InProgressPersonalLoanDueDateIntentHandler;
import com.amazon.ask.banking.handlers.InProgressPersonalLoanPaymentIntentHandler;
import com.amazon.ask.banking.handlers.InProgressPersonalLoanPinIntentHandler;
import com.amazon.ask.banking.handlers.LaunchRequestHandler;
import com.amazon.ask.banking.handlers.RepeatIntentHandler;
import com.amazon.ask.banking.handlers.SessionEndedRequestHandler;
import com.amazon.ask.banking.handlers.StartedAccountBalanceIntentHandler;
import com.amazon.ask.banking.handlers.StartedCreditCardIntentHandler;
import com.amazon.ask.banking.handlers.StartedLinkBankIntentHandler;
import com.amazon.ask.banking.handlers.StartedPersonalLoanIntentHandler;
import com.amazon.ask.banking.interceptors.RequestSegmentInterceptor;
import com.amazon.ask.banking.interceptors.ResponseSegmentInterceptor;

public class BankingStreamHandler extends SkillStreamHandler {

    @SuppressWarnings("unchecked")
	private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new CancelandStopIntentHandler(),
                        new HelpIntentHandler(),
                        new LaunchRequestHandler(),
                        new RepeatIntentHandler(),
                        new InProgressAccountBalanceIntentHandler(),
                        new InProgressCreditCardPinIntentHandler(),
                        new InProgressCreditCardDueDateIntentHandler(),
                        new InProgressCreditCardPaymentIntentHandler(),
                        new InProgressPersonalLoanPinIntentHandler(),
                        new InProgressPersonalLoanDueDateIntentHandler(),
                        new InProgressPersonalLoanPaymentIntentHandler(),
                        new InProgressLinkBankPinIntentHandler(),
                        new InProgressLinkBankRoutingIntentHandler(),
                        new InProgressLinkBankAccountIntentHandler(),
                        new InProgressLinkBankCategoryIntentHandler(),
                        new CompletedAccountBalanceIntentHandler(),
                        new CompletedCreditCardIntentHandler(),
                        new CompletedLinkBankIntentHandler(),
                        new CompletedPersonalLoanIntentHandler(),
                        new StartedAccountBalanceIntentHandler(),
                        new StartedLinkBankIntentHandler(),
                        new StartedCreditCardIntentHandler(),
                        new StartedPersonalLoanIntentHandler(),
                        new FallbackIntentHandler(),
                        new SessionEndedRequestHandler())
                .addExceptionHandler(new ExceptionIntentHandler())
                .addRequestInterceptor(new RequestSegmentInterceptor())
                .addResponseInterceptor(new ResponseSegmentInterceptor())                
                .withSkillId("amzn1.ask.skill.724223b3-39d3-4a6a-8496-6c6436b6805b")
                .build();
    }

    public BankingStreamHandler() {
        super(getSkill());
    }

}
