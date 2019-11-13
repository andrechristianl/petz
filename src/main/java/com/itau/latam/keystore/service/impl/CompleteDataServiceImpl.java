package com.itau.latam.keystore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itau.latam.keystore.cipher.util.AESCBC256;
import com.itau.latam.keystore.model.dto.CompleteDataDTO;
import com.itau.latam.keystore.repository.KeyStoreRepository;
import com.itau.latam.keystore.repository.entity.KeyStore;
import com.itau.latam.keystore.service.CompleteDataService;

@Service
public class CompleteDataServiceImpl implements CompleteDataService {

    @Autowired
    private KeyStoreRepository keyStoreRepository;

    @Override
    public List<CompleteDataDTO> encryptData(List<CompleteDataDTO> completeDataDTO) {
        KeyStore keyStore = this.keyStoreRepository.findAllCreateDate().get(0);
        AESCBC256.validateCipherSuite(keyStore.getSecretKey(), keyStore.getSalt(), String.valueOf(keyStore.getId()));
        completeDataDTO.stream().forEach(c -> {
            String encryptedData = AESCBC256.generateFinalEncryptedData(c.getPlaintext());
            c.setEncryptted(encryptedData);
        });
        return completeDataDTO;
    }

    @Override
    public List<CompleteDataDTO> decryptData(List<CompleteDataDTO> completeDataDTO) {
        completeDataDTO.stream().forEach(c -> {
            String idKey = AESCBC256.findIdKeyFromEncriptedData(c.getEncryptted());
            KeyStore keyStore = keyStoreRepository.findById(Integer.parseInt(idKey));
            AESCBC256.validateCipherSuite(
                keyStore.getSecretKey(), 
                keyStore.getSalt(), 
                String.valueOf(keyStore.getId())
            );
            String decryptedData = AESCBC256.generateFinalDecryptedData(c.getEncryptted());
            c.setPlaintext(decryptedData);
        });
        return completeDataDTO;
    }
}
