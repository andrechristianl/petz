package com.itau.latam.keystore.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itau.latam.cipher.util.AESCBC256;
import com.itau.latam.core.service.impl.FormatterServiceImpl;
import com.itau.latam.keystore.model.dto.CompleteDataDTO;
import com.itau.latam.keystore.model.dto.KeyStoreDTO;
import com.itau.latam.keystore.repository.KeyStoreRepository;
import com.itau.latam.keystore.repository.entity.KeyStore;
import com.itau.latam.keystore.service.KeyStoreService;
import com.itau.latam.keystore.service.business.EncryptionDataBusinessService;

@Service
public class KeyStoreServiceImpl implements KeyStoreService {
	@Autowired
	private KeyStoreRepository keyTableRepository;

	@Autowired
	private EncryptionDataBusinessService businessDataService;

	@Override
	public KeyStoreDTO findByDate() {
		KeyStore mainTable = this.keyTableRepository.findLatestDate();
		KeyStoreDTO mainTableDTO = FormatterServiceImpl.copyObject(mainTable, KeyStoreDTO.class);
		return mainTableDTO;
	}

	@Override
	public KeyStoreDTO findById(int id) {
		KeyStore mainTable = this.keyTableRepository.findById(id);
		KeyStoreDTO mainTableDTO = FormatterServiceImpl.copyObject(mainTable, KeyStoreDTO.class);
		return mainTableDTO;
	}

	@Override
	public List<CompleteDataDTO> encryptListData(List<CompleteDataDTO> completeDataDTO) {
		return businessDataService.encryptListData(completeDataDTO, findByDate());
	}

	@Override
	public List<CompleteDataDTO> decryptListData(List<CompleteDataDTO> completeDataDTO) {

		Map<Integer, KeyStoreDTO> mapKeys = new HashMap<Integer, KeyStoreDTO>();

		completeDataDTO.stream().forEach(c -> {
			String encryptedText = c.getEncryptted();
			if (encryptedText == null || encryptedText.isEmpty()) {
				throw new ServiceException(
						"An issue has been raised because one or more of the provided encrypted fields were either null or empty");
			}
			String idKey = AESCBC256.findIdKeyFromEncriptedData(encryptedText);

			KeyStoreDTO keyStore = findById(Integer.parseInt(idKey));
			mapKeys.put(Integer.parseInt(idKey), keyStore);
		});

		return businessDataService.decryptListData(completeDataDTO, mapKeys);
	}

}
