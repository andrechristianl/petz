package com.itau.latam.keystore.service;

import com.itau.latam.keystore.model.dto.KeyStoreDTO;

public interface KeyStoreService {
	
	KeyStoreDTO findById(int id);

    KeyStoreDTO findByDate();
}
