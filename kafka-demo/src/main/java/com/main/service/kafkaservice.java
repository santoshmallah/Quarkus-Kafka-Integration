package com.main.service;

import com.main.moddel.PanDTO;
import com.main.moddel.VerificationData;

public interface kafkaservice {
	public void processData(VerificationData verificationData);

	public String panVerification(PanDTO pan);

	String sendMobileOtp(String number);

	boolean verifyOtp(String mobileNumber, String userProvidedOtp);
}
