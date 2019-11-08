package com.itau.latam.keystore.service;

import java.util.List;

import com.itau.latam.keystore.model.dto.CompleteDataDTO;

public interface CompleteDataService {

	List<CompleteDataDTO> encrypt(CompleteDataDTO completeData);

	List<CompleteDataDTO> decrypt(CompleteDataDTO completeData);

}
