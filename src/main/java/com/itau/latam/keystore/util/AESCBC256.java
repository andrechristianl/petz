package com.itau.latam.keystore.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

public class AESCBC256 {
    private static final String DEFAULT_ID_CRIPT_SEPARATOR = ".";
    private static Map<String, CipherSuite> ciphers;

    private static String lastId;

    static {
        ciphers = new HashMap<String, CipherSuite>();
    }

    public static String generateFinalDecryptedData(String encodedInitialString) throws Exception {
        String[] composedArray = generateComposedKey(encodedInitialString);
        String encodedId = composedArray[0];
        String encriptedData = composedArray[1];
        return AESCBC256.decrypt(encriptedData, decodeFromBase64(encodedId));

    }

    public static String findIdKeyFromEncriptedData(String encriptedString) throws UnsupportedEncodingException {
        return decodeFromBase64(generateComposedKey(encriptedString)[0]);
    }

    private static String[] generateComposedKey(String encodedString) throws UnsupportedEncodingException {
        String decodedString = decodeFromBase64(encodedString);
        String[] composedArray = decodedString.split("\\" + DEFAULT_ID_CRIPT_SEPARATOR, 2);
        return composedArray;
    }

    public static String generateFinalEncryptedData(String originalString) throws Exception {
        String encryptedString = AESCBC256.encrypt(originalString);
        String encodedId = encodeToBase64(lastId);
        String finalString = createFinalHashEncripted(encodedId, encryptedString);
        return finalString;
    }

    private static String encrypt(String stringToEncrypt) throws Exception {
        String finalStr = "";
        CipherSuite cipherSuite = findCipherById(lastId);
        Cipher cipher = cipherSuite.getCipher();

        synchronized (cipher) {
            cipher.init(Cipher.ENCRYPT_MODE, cipherSuite.getSecretKey(), cipherSuite.getIvspec());
            finalStr = Base64.getEncoder().encodeToString(cipher.doFinal(stringToEncrypt.getBytes("UTF-8")));
        }
        return finalStr;
    }

    private static String decrypt(String stringToDecrypt, String keyId) {
        CipherSuite cipherSuite = findCipherById(keyId);
        Cipher cipher = cipherSuite.getCipher();

        try {
            cipher.init(Cipher.DECRYPT_MODE, cipherSuite.getSecretKey(), cipherSuite.getIvspec());
            return new String(cipher.doFinal(Base64.getDecoder().decode(stringToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    public static synchronized void validateCipherSuite(String secretKey, String salt, String cipherKey) {
        CipherSuite selectedCipher = ciphers.get(cipherKey);

        if (selectedCipher == null) {
            CipherSuite c = null;
            try {
                lastId = cipherKey;
                c = new CipherSuite(secretKey, salt);
            } catch (Exception e) {
                e.printStackTrace();
            }

            ciphers.put(cipherKey, c);
        }

    }

    private static CipherSuite findCipherById(String id) {
        CipherSuite selectedCipher = ciphers.get(id);
        if (selectedCipher == null) {
            throw new RuntimeException("cifra não foi criada");
        }
        return selectedCipher;
    }

    private static String createFinalHashEncripted(String id, String encriptedString) throws UnsupportedEncodingException {
        String finalEncoded = encodeToBase64(id + DEFAULT_ID_CRIPT_SEPARATOR + encriptedString);
        return finalEncoded;
    }

    public static String decodeFromBase64(String myStr) throws UnsupportedEncodingException {
        return new String(Base64.getDecoder().decode(myStr));
    }

    private static String encodeToBase64(String myStr) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(myStr.getBytes("UTF-8"));
    }

}