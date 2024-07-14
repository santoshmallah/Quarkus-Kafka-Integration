package com.main.moddel;

public class VerificationData {
	
	private String panNumber;
	private int mobileNumber;
	private int otp;
	
	public int getOtp() {
		return otp;
	}
	public void setOtp(int otp) {
		this.otp = otp;
	}
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	public int getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	@Override
	public String toString() {
		return "VerificationData [panNumber=" + panNumber + ", mobileNumber=" + mobileNumber + ", otp=" + otp + "]";
	}
}
