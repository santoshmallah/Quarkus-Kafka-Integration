package com.main;

import org.jboss.logging.Logger;

import com.main.moddel.PanDTO;
import com.main.moddel.VerificationData;
import com.main.service.kafkaservice;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;

@Path("/kafka")
public class Controller {

	private static final Logger LOG = Logger.getLogger(Controller.class);

	@Inject
	kafkaservice kafkaservice;

	@POST
	@Path("/v1/panvalidation")
	public String panValidation(PanDTO panDTO) {
		String resposne = null;
		try {
			resposne = kafkaservice.panVerification(panDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resposne;
	}

	@GET
	@Path("/v1/verifyMobile")
	public String verifyMobile(String mobileNumber) {
		String reponseString = null;
		try {
			reponseString = kafkaservice.sendMobileOtp(mobileNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reponseString;
	}

	@GET
	@Path("/v1/mobileOtpValidation")
	public boolean mobileOtpValidation(String mobileNumber, String otp) {
		boolean response = false;
		try {
			response = kafkaservice.verifyOtp(mobileNumber, otp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@POST
	@Path("/v1/processdata")
	@Consumes(MediaType.APPLICATION_JSON)
	public String sendEventToKafka(VerificationData verificationData) {
		try {
			LOG.info("Calling");
			kafkaservice.processData(verificationData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "done";

	}

}
