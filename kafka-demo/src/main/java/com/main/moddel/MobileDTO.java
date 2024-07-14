package com.main.moddel;

public class MobileDTO {

	private String mobileNumber;
	private String otp;
	private String status;
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "MobileDTO [mobileNumber=" + mobileNumber + ", otp=" + otp + ", status=" + status + "]";
	}
}
