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
                    // Inserir chamada do serviço encriptador
                    c.setEncryptted(AESCBC256.encrypt(c.getPlaintext()));
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
                    // Inserir chamada do serviço decryptador
                    c.setPlaintext(AESCBC256.decrypt(c.getEncryptted()));
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
