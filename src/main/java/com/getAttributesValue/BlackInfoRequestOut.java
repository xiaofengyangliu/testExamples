package com.getAttributesValue;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BlackInfoRequestOut {
	
	private String token;
	@JsonProperty("idcard")
	String cardNo;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	
}
