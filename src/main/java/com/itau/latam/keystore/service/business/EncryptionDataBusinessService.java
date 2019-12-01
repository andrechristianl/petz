package com.itau.latam.keystore.service.business;

import java.util.List;
import java.util.Map;

import com.itau.latam.keystore.model.dto.CompleteDataDTO;
import com.itau.latam.keystore.model.dto.KeyStoreDTO;

public interface EncryptionDataBusinessService {

	List<CompleteDataDTO> encryptListData(List<CompleteDataDTO> completeData, KeyStoreDTO keyStore);

	List<CompleteDataDTO> decryptListData(List<CompleteDataDTO> completeDataDTO, Map<Integer, KeyStoreDTO> mapKeys);

}
