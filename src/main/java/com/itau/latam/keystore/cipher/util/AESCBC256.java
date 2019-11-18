package com.itau.latam.keystore.cipher.util;

import static com.itau.latam.keystore.cipher.util.EncodingUtil.decodeFromBase64;
import static com.itau.latam.keystore.cipher.util.EncodingUtil.encodeToBase64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public class AESCBC256 {

    
    static String LAST_ID;
    

    public static String generateDecryptedData(String encodedInitialString) {
        String[] composedArray = CipherSuiteManager.generateComposedKey(encodedInitialString);
        String encodedId = composedArray[0];
        String encriptedData = composedArray[1];
        
        String decryptedString = "";
        try {
            decryptedString = CipherSuiteManager.decrypt(encriptedData, decodeFromBase64(encodedId));
        } catch (InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException("There has been an issue while trying to decrypt the data");
        }
        
        return decryptedString;
    }
    
    public static String generateEncryptedData(String originalString) {
        String finalString = "";
        try {
            String encryptedString = CipherSuiteManager.encrypt(originalString);
            String encodedId = encodeToBase64(LAST_ID);
            finalString = CipherSuiteManager.createFinalHashEncripted(encodedId, encryptedString);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
            throw new RuntimeException("There has been an issue while trying to encrypt the data");
        }
        return finalString;
    }

    public static String findIdKeyFromEncriptedData(String encriptedString){
        return decodeFromBase64(CipherSuiteManager.generateComposedKey(encriptedString)[0]);
    }
    
    
    
	public static synchronized void validateCipherSuite(String secretKey, String salt, String id) {
		CipherSuiteManager.validateCipherSuite(secretKey, salt, id);
	}

}