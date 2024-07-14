package com.main.serviceImpl;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.moddel.MobileDTO;
import com.main.moddel.PanDTO;
import com.main.moddel.VerificationData;
import com.main.service.kafkaservice;

import io.smallrye.reactive.messaging.kafka.Record;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class kafkaserviceImpl implements kafkaservice {

	private static final Logger LOG = Logger.getLogger(kafkaserviceImpl.class);

	@Inject
	OtpService otpService;

	@Inject
	@Channel("ProcessData_topic")
	Emitter<Record<String, String>> emitter;
	
	private final Map<String, String> otpStorage = new HashMap<>();

	@Override
	public void processData(VerificationData verificationData) {
		try {
			LOG.info("service call");
			ObjectMapper objectMapper = new ObjectMapper();
			String payload = objectMapper.writeValueAsString(verificationData);

			emitter.send(Record.of("request", payload));

			LOG.info("Data send successfull");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String panVerification(PanDTO pan) {
		String responseData = null;
		try {

			LOG.info("service call");
			String apiUrl = "https://dg-sandbox.setu.co/api/verify/pan";

			ObjectMapper objectMapper = new ObjectMapper();
			String requestBody = objectMapper.writeValueAsString(pan);

			HttpClient client = HttpClient.newHttpClient();

			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiUrl))
					.header("Content-Type", "application/json")
					.header("x-client-id", "b62ace35-122c-4a18-9b5c-f60b2a6a2f9c")
					.header("x-client-secret", "UoZYWrl8NVc10XysYLPCY61sD6JQfGxg")
					.header("x-product-instance-id", "b0b1114a-c48e-468f-bd6b-982b0e9a8c39")
					.POST(HttpRequest.BodyPublishers.ofString(requestBody)).build();

			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			responseData = response.body();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseData;
	}

	@Override
	public String sendMobileOtp(String number) {
		String reponseString = null;
		try {
			MobileDTO mobileDTO = new MobileDTO();
			LOG.info("service call");
			String otpCode = otpService.generateOtp();
//			storing otp for validation
			otpStorage.put(number, otpCode);
			otpService.sendOtp(number, otpCode);
			mobileDTO.setMobileNumber(number);
			mobileDTO.setOtp(otpCode);
			mobileDTO.setStatus("OTP sent successfully.");
			ObjectMapper objectMapper = new ObjectMapper();
			reponseString = objectMapper.writeValueAsString(mobileDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reponseString;
	}
	
	@Override
	public boolean verifyOtp(String mobileNumber, String userProvidedOtp) {
        String storedOtp = otpStorage.get(mobileNumber);
        return storedOtp != null && storedOtp.equals(userProvidedOtp);
    }

}
