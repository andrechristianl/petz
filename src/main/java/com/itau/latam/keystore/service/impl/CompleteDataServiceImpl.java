package com.itau.latam.keystore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itau.latam.keystore.model.dto.CompleteDataDTO;
import com.itau.latam.keystore.repository.KeyStoreRepository;
import com.itau.latam.keystore.repository.entity.KeyStore;
import com.itau.latam.keystore.service.CompleteDataService;
import com.itau.latam.keystore.util.AESCBC256;

@Service
public class CompleteDataServiceImpl implements CompleteDataService {

    @Autowired
    private KeyStoreRepository keyStoreRepository;

    @Override
    public List<CompleteDataDTO> encryptData(List<CompleteDataDTO> completeDataDTO) {
        KeyStore keyStore = this.keyStoreRepository.findAllCreateDate().get(0);
        AESCBC256.validateCipherSuite(keyStore.getSecretKey(), keyStore.getSalt(), String.valueOf(keyStore.getId()));
        try {
            completeDataDTO.stream().forEach(c -> {
                try {
                    String encryptedData = AESCBC256.generateFinalEncryptedData(c.getPlaintext());
                    
                    c.setEncryptted(encryptedData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            return completeDataDTO;
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
            return null;
        }
    }

    @Override
    public List<CompleteDataDTO> decryptData(List<CompleteDataDTO> completeDataDTO) {
        try {
            completeDataDTO.stream().forEach(c -> {
                try {
                    String idKey = AESCBC256.findIdKeyFromEncriptedData(c.getEncryptted());
                    KeyStore keyStore = keyStoreRepository.findById(Integer.parseInt(idKey));
                    AESCBC256.validateCipherSuite(keyStore.getSecretKey(), keyStore.getSalt(), String.valueOf(keyStore.getId()));
                    
                    String decryptedData = AESCBC256.generateFinalDecryptedData(c.getEncryptted());
                    c.setPlaintext(decryptedData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            return completeDataDTO;
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
            return null;
        }
    }
}
