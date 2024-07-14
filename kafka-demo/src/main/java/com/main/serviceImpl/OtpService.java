package com.main.serviceImpl;

import java.security.SecureRandom;

import org.eclipse.microprofile.config.inject.ConfigProperties;

import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OtpService {
	
	private static final SecureRandom secureRandom = new SecureRandom();
    private static final int OTP_LENGTH = 6;
	
	@ConfigProperties(prefix="twilio.phoneNumber")
    private String twilioPhoneNumber;

    private final TwilioRestClient twilioRestClient;

    public OtpService(@ConfigProperties(prefix="twilio.accountSid") String accountSid,
    		@ConfigProperties(prefix="twilio.authToken") String authToken) {
        twilioRestClient = new TwilioRestClient.Builder(accountSid, authToken).build();
    }

    public String generateOtp() {
    	int otp = 0;
		try {
			otp = secureRandom.nextInt(900000) + 100000;
		} catch (Exception e) {
			e.printStackTrace();
		}
        return String.valueOf(otp);
    }

    public void sendOtp(String recipientPhoneNumber, String otpCode) {
        Message.creator(new PhoneNumber(recipientPhoneNumber), new PhoneNumber(twilioPhoneNumber),
                "Your OTP code is: " + otpCode)
                .create(twilioRestClient);
    }

}
