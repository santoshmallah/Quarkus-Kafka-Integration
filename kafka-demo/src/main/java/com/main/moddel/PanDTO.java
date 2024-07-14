package com.main.moddel;

public class PanDTO {
	
	private String pan;
	private String consent;
	private String reason;
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getConsent() {
		return consent;
	}
	public void setConsent(String consent) {
		this.consent = consent;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	@Override
	public String toString() {
		return "PanDTO [pan=" + pan + ", consent=" + consent + ", reason=" + reason + "]";
	}

}
