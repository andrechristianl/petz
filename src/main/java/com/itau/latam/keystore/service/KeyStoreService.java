package com.itau.latam.keystore.service;

import java.util.List;

import com.itau.latam.keystore.model.dto.CompleteDataDTO;
import com.itau.latam.keystore.model.dto.KeyStoreDTO;

public interface KeyStoreService {

	KeyStoreDTO findById(int id);

	KeyStoreDTO findByDate();

	List<CompleteDataDTO> encryptListData(List<CompleteDataDTO> completeDataDTO);

	List<CompleteDataDTO> decryptListData(List<CompleteDataDTO> completeDataDTO);
}
