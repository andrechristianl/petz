package com.itau.latam.keystore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itau.latam.keystore.model.dto.KeyTableDTO;
import com.itau.latam.keystore.repository.KeyTableRepository;
import com.itau.latam.keystore.repository.entity.KeyTable;
import com.itau.latam.keystore.service.KeyTableService;

@Service
public class KeyTableServiceImpl implements KeyTableService{
	@Autowired
	private KeyTableRepository keyTableRepository;
	
	
	@Override
	public List<KeyTableDTO> findBydateCreated(String date) {
		List <KeyTable> keyTable = this.keyTableRepository.findBydateCreated(date);
		List <KeyTableDTO> keyTableDTO = FormatterServiceImpl.convertEntityToDTO(keyTable, KeyTableDTO.class);
		return keyTableDTO;
	}

	@Override
	public List<KeyTableDTO> findById(int id) {
		List<KeyTable> keyTable = this.keyTableRepository.findById(id);
		List <KeyTableDTO> keyTableDTO = FormatterServiceImpl.convertEntityToDTO(keyTable, KeyTableDTO.class);
		return keyTableDTO;
	}
}
