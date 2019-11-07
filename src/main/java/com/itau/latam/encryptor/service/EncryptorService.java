package com.itau.latam.encryptor.service;

import java.util.List;

import com.itau.latam.encryptor.model.dto.EncryptorDTO;

public interface EncryptorService {
	List<EncryptorDTO> findBydateCreated();
	
	List<EncryptorDTO> findById(int id);
}
