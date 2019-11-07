package com.itau.latam.keystore.model.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class KeyTableDTO {
	@Column(name = "id")
	private int id;
	
	@NotEmpty
	@Column(name="secret_key")
	private String key;
	
	@NotEmpty
	@Column(name="salt")
	private String salt;
	
	@NotEmpty
	@Column(name="created_date")
	private String dateCreated;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
}
