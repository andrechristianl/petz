package com.itau.latam.keystore.service;

import java.util.List;

import com.itau.latam.keystore.model.dto.CompleteDataDTO;

public interface CompleteDataService {

	List<CompleteDataDTO> encryptData(List<CompleteDataDTO> completeData);

	List<CompleteDataDTO> decryptData(List<CompleteDataDTO> completeData);

}
