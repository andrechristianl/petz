package com.itau.latam.keystore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itau.latam.keystore.model.dto.KeyStoreDTO;
import com.itau.latam.keystore.repository.KeyStoreRepository;
import com.itau.latam.keystore.repository.entity.KeyStore;
import com.itau.latam.keystore.service.KeyStoreService;

@Service
public class KeyStoreServiceImpl implements KeyStoreService{
	@Autowired
	private KeyStoreRepository keyTableRepository;
	
	
	@Override
	public List<KeyStoreDTO> findByDate() {
		List<KeyStore> mainTable = this.keyTableRepository.findAllCreateDate();
		List<KeyStoreDTO> mainTableDTO = FormatterServiceImpl.convertEntityToDTO( mainTable, KeyStoreDTO.class);
		return  mainTableDTO;
	}

	@Override
	public List<KeyStoreDTO> findById(int id) {
		List<KeyStore> mainTable = this.keyTableRepository.findById(id);
		List <KeyStoreDTO> mainTableDTO = FormatterServiceImpl.convertEntityToDTO(mainTable, KeyStoreDTO.class);
		return mainTableDTO;
	}
}
