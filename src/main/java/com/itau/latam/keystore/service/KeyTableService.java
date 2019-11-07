package com.itau.latam.keystore.service;

import java.util.List;

import com.itau.latam.keystore.model.dto.KeyTableDTO;

public interface KeyTableService {
	List<KeyTableDTO> findBydateCreated(String date);
	
	List<KeyTableDTO> findById(int id);
}
