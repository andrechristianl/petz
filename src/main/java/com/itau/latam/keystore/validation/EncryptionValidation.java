package com.itau.latam.keystore.validation;

import org.hibernate.service.spi.ServiceException;


public class EncryptionValidation {
    public static void validateRules(String encryptedTextString) {
        if (encryptedTextString.length() >= 56) {
            throw new ServiceException("The message to be encrypted cannot have more than 56 characters");
        }
        
        
    }
}
