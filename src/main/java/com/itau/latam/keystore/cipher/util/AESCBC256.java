package com.itau.latam.keystore.cipher.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class AESCBC256 {
    private static final String DEFAULT_ID_CRIPT_SEPARATOR = ".";
    
    private static Map<String, CipherSuite> ciphers;
    private static String lastId;
    
    static {
        ciphers = new HashMap<String, CipherSuite>();
    }
    

    public static synchronized void validateCipherSuite(String secretKey, String salt, String id) {
        CipherSuite selectedCipher = ciphers.get(id);
        if (selectedCipher == null) {
            CipherSuite c = null;
            try {
                c = new CipherSuite(secretKey, salt);
            } catch (NullPointerException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException e) {
                throw new RuntimeException("There has been an issue while trying to initialize the Cipher");
            }
            lastId = id;
            ciphers.put(id, c);
        }
    }

    public static String generateFinalDecryptedData(String encodedInitialString) {
        String[] composedArray = generateComposedKey(encodedInitialString);
        String encodedId = composedArray[0];
        String encriptedData = composedArray[1];
        
        String decryptedString = "";
        try {
            decryptedString = AESCBC256.decrypt(encriptedData, decodeFromBase64(encodedId));
        } catch (InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException("There has been an issue while trying to decrypt the data");
        }
        
        return decryptedString;
    }
    
    public static String generateFinalEncryptedData(String originalString) {
        String finalString = "";
        try {
            String encryptedString = AESCBC256.encrypt(originalString);
            String encodedId = encodeToBase64(lastId);
            finalString = createFinalHashEncripted(encodedId, encryptedString);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
            throw new RuntimeException("There has been an issue while trying to encrypt the data");
        }
        return finalString;
    }

    public static String findIdKeyFromEncriptedData(String encriptedString){
        return decodeFromBase64(generateComposedKey(encriptedString)[0]);
    }

    private static String[] generateComposedKey(String encodedString) {
        return decodeFromBase64(encodedString).split("\\" + DEFAULT_ID_CRIPT_SEPARATOR, 2);
    }

    private static String encrypt(String stringToEncrypt) throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException{
        String finalStr = "";
        CipherSuite cipherSuite = findCipherById(lastId);
        Cipher cipher = cipherSuite.getCipher();
        synchronized (cipher) {
            cipher.init(Cipher.ENCRYPT_MODE, cipherSuite.getSecretKey(), cipherSuite.getIvspec());
            finalStr = Base64.getEncoder().encodeToString(cipher.doFinal(stringToEncrypt.getBytes(StandardCharsets.UTF_8.name())));
        }
        return finalStr;
    }

    private static String decrypt(String stringToDecrypt, String keyId) throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        CipherSuite cipherSuite = findCipherById(keyId);
        Cipher cipher = cipherSuite.getCipher();
        cipher.init(Cipher.DECRYPT_MODE, cipherSuite.getSecretKey(), cipherSuite.getIvspec());
        return new String(cipher.doFinal(Base64.getDecoder().decode(stringToDecrypt)));
    }

    private static CipherSuite findCipherById(String id) {
        CipherSuite selectedCipher = id == null ? null : ciphers.get(id);
        if (selectedCipher == null) {
            throw new RuntimeException("There has been an issue while trying to retrieve the Cipher");
        }
        return selectedCipher;
    }

    private static String createFinalHashEncripted(String id, String encriptedString) throws UnsupportedEncodingException {
        return encodeToBase64(id + DEFAULT_ID_CRIPT_SEPARATOR + encriptedString);
    }

    public static String decodeFromBase64(String myStr) {
        return new String(Base64.getDecoder().decode(myStr));
    }

    private static String encodeToBase64(String myStr) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(myStr.getBytes(StandardCharsets.UTF_8.name()));
    }
}