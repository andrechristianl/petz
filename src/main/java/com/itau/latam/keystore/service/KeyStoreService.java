package com.itau.latam.keystore.service;

import java.util.List;
import com.itau.latam.keystore.model.dto.KeyStoreDTO;

public interface KeyStoreService {
	
	KeyStoreDTO findById(int id);

	List<KeyStoreDTO> findByDate();
}
