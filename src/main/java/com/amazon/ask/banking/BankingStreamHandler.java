package com.amazon.ask.banking;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import com.amazon.ask.banking.exception.ExceptionIntentHandler;
import com.amazon.ask.banking.handlers.CancelandStopIntentHandler;
import com.amazon.ask.banking.handlers.CompletedAccountBalanceIntentHandler;
import com.amazon.ask.banking.handlers.CompletedCreditCardIntentHandler;
import com.amazon.ask.banking.handlers.FallbackIntentHandler;
import com.amazon.ask.banking.handlers.HelpIntentHandler;
import com.amazon.ask.banking.handlers.InProgressAccountBalanceIntentHandler;
import com.amazon.ask.banking.handlers.InProgressCreditCardDueDateIntentHandler;
import com.amazon.ask.banking.handlers.InProgressCreditCardPaymentIntentHandler;
import com.amazon.ask.banking.handlers.LaunchRequestHandler;
import com.amazon.ask.banking.handlers.RepeatIntentHandler;
import com.amazon.ask.banking.handlers.SessionEndedRequestHandler;
import com.amazon.ask.banking.handlers.StartedAccountBalanceIntentHandler;
import com.amazon.ask.banking.handlers.StartedCreditCardIntentHandler;
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
                        new InProgressCreditCardDueDateIntentHandler(),
                        new InProgressCreditCardPaymentIntentHandler(),
                        new CompletedAccountBalanceIntentHandler(),
                        new CompletedCreditCardIntentHandler(),
                        new StartedAccountBalanceIntentHandler(),
                        new StartedCreditCardIntentHandler(),
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
