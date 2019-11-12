package com.itau.latam.keystore.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itau.latam.keystore.model.dto.CompleteDataDTO;
import com.itau.latam.keystore.service.CompleteDataService;
import com.itau.latam.keystore.util.AESCBC256;

@Service
public class CompleteDataServiceImpl implements CompleteDataService {

    @Override
    public List<CompleteDataDTO> encryptData(List<CompleteDataDTO> completeDataDTO) {
        try {
            completeDataDTO.stream().forEach(c -> {
                try {
                    String encryptedData = AESCBC256.generateFinalEncryptedData(c.getPlaintext());
                    c.setEncryptted(encryptedData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
            );
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
                    String decryptedData = AESCBC256.generateFinalDecryptedData(c.getEncryptted());
                    c.setPlaintext(decryptedData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            );
            return completeDataDTO;

        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
            return null;
        }
    }
}
