package com.itau.latam.keystore.validation;

import org.hibernate.service.spi.ServiceException;

public class DecryptionValidation {

    public static void validateRules(String clearTextString) {
        if (clearTextString.length() >= 125) {
            throw new ServiceException("The message to be decrypted cannot have more than 56 characters");
        }
        
    }

}
