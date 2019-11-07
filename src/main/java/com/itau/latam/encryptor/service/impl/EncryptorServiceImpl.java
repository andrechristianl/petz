package com.itau.latam.encryptor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itau.latam.encryptor.model.dto.EncryptorDTO;
import com.itau.latam.encryptor.repository.EncryptorRepository;
import com.itau.latam.encryptor.repository.entity.Encryptor;
import com.itau.latam.encryptor.service.EncryptorService;

@Service
public class EncryptorServiceImpl implements EncryptorService{
	@Autowired
	private EncryptorRepository encryptorRepository;
	
	
	@Override
	public List<EncryptorDTO> findBydateCreated() {
		List <Encryptor> encryptor = this.encryptorRepository.findBydateCreated();
		List <EncryptorDTO> encryptorDTO = FormatterServiceImpl.convertEntityToDTO(encryptor, EncryptorDTO.class);
		return encryptorDTO;
	}

	@Override
	public List<EncryptorDTO> findById(int id) {
		List<Encryptor> encryptor = this.encryptorRepository.findById(id);
		List <EncryptorDTO> encryptorDTO = FormatterServiceImpl.convertEntityToDTO(encryptor, EncryptorDTO.class);
		return encryptorDTO;
	}
}
