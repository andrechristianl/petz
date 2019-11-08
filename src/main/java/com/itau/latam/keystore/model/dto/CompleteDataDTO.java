package com.itau.latam.keystore.model.dto;

import java.util.List;

public class CompleteDataDTO {
	
	private List<?> plaintext;
	
	private List<?> encryptted;

	public List<?> getPlaintext() {
		return plaintext;
	}

	public void setPlaintext(List<?> plaintext) {
		this.plaintext = plaintext;
	}

	public List<?> getEncryptted() {
		return encryptted;
	}

	public void setEncryptted(List<?> encryptted) {
		this.encryptted = encryptted;
	}

		
}
