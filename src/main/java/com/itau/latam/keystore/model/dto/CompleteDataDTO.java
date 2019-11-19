package com.itau.latam.keystore.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CompleteDataDTO {
	
	@JsonProperty("plaintext")
	private String plaintext;
	
	@JsonProperty("encryptted")
	private String encryptted;
	
	public String getPlaintext() {
		return plaintext;
	}

	public void setPlaintext(String plaintext) {
		this.plaintext = plaintext;
	}

	public String getEncryptted() {
		return encryptted;
	}

	public void setEncryptted(String encryptted) {
		this.encryptted = encryptted;
	}

}
