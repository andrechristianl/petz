package com.itau.latam.keystore.service.business.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import com.itau.latam.cipher.util.AESCBC256;
import com.itau.latam.keystore.model.dto.CompleteDataDTO;
import com.itau.latam.keystore.model.dto.KeyStoreDTO;
import com.itau.latam.keystore.service.business.EncryptionDataBusinessService;

@Service
public class EncryptionDataBusinessServiceImpl implements EncryptionDataBusinessService {

	@Override
	public List<CompleteDataDTO> encryptListData(List<CompleteDataDTO> completeDataDTO, KeyStoreDTO keyStore) {

		AESCBC256.validateCipherSuite(keyStore.getSecretKey(), keyStore.getSalt(), String.valueOf(keyStore.getId()));

		completeDataDTO.stream().forEach(c -> {
			String encryptedData = AESCBC256.generateEncryptedData(c.getPlaintext());
			c.setEncryptted(encryptedData);
		});
		return completeDataDTO;

	}
	
	@Override
    public List<CompleteDataDTO> decryptListData(List<CompleteDataDTO> completeDataDTO, Map<Integer, KeyStoreDTO> mapKeys) {
		
        completeDataDTO.stream().forEach(c -> {
            String encryptedText = c.getEncryptted();
            if (encryptedText == null || encryptedText.isEmpty()) {
                throw new ServiceException("An issue has been raised because one or more of the provided encrypted fields were either null or empty");
            }
            String idKey = AESCBC256.findIdKeyFromEncriptedData(encryptedText);
            KeyStoreDTO keyStore = mapKeys.get(Integer.parseInt(idKey));
            
            
            AESCBC256.validateCipherSuite(
                keyStore.getSecretKey(), 
                keyStore.getSalt(), 
                String.valueOf(keyStore.getId())
            );
            
            String decryptedData = AESCBC256.generateDecryptedData(c.getEncryptted());
            c.setPlaintext(decryptedData);
        });
        return completeDataDTO;
    }

}
