package com.itau.latam.keystore.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

public class AESCBC256 {
    private static final String DEFAULT_ID_CRIPT_SEPARATOR = ".";
    private static Map<String, CipherSuite> ciphers;

    private static final String LAST_ID = "1";

    static {
        // TODO: vou até o banco dea dados e busco o lastId

        String secretKeyStr = "aSDG5343F24F5THRTU7trj57wbwbdhnngfdghdtf3FdDedss34DfgMjKiKij3kjd";
        String salt = "MmskKjjhuiop97654tgGghjklskskjhG54rfgghbNhgbhNjhjUjhYhgTfDvnMmhGf";

        ciphers = new HashMap<String, CipherSuite>();
        CipherSuite c = null;
        try {
            c = new CipherSuite(secretKeyStr, salt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ciphers.put(LAST_ID, c);
    }

    public static String generateFinalDecryptedData(String encriptedString) throws Exception {
        String completeDecript = decodeFromBase64(encriptedString);
        String[] arrEncriptedData = completeDecript.split("\\" + DEFAULT_ID_CRIPT_SEPARATOR, 2);

        // TODO: Validar se o split está consistente

        String encodedId = arrEncriptedData[0];
        String encriptedData = arrEncriptedData[1];
        return AESCBC256.decrypt(encriptedData, decodeFromBase64(encodedId));

    }

    public static String generateFinalEncryptedData(String originalString) throws Exception {
        String encryptedString = AESCBC256.encrypt(originalString);
        String encodedId = encodeToBase64("1");
        String finalString = createFinalHashEncripted(encodedId, encryptedString);
        return finalString;

    }
    
    private static String encrypt(String strToEncrypt) throws Exception {
        String finalStr = "";

        CipherSuite cipherSuite = findCipherById(LAST_ID);
        Cipher cipher = cipherSuite.getCipher();
        
        synchronized (cipher) {
            cipher.init(Cipher.ENCRYPT_MODE, cipherSuite.getSecretKey(), cipherSuite.getIvspec());
            finalStr = Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        }
        return finalStr;
    }

    private static String decrypt(String strToDecrypt, String keyId) {

        CipherSuite cipherSuite = findCipherById(keyId);
        Cipher cipher = cipherSuite.getCipher();

        try {
            cipher.init(Cipher.DECRYPT_MODE, cipherSuite.getSecretKey(), cipherSuite.getIvspec());
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    private static CipherSuite findCipherById(String keyId) {
        CipherSuite selectedCipher = null;

        selectedCipher = ciphers.get(keyId);
        if (selectedCipher == null) {
            // TODO: 1. Ir ao banco de dados e busco a string da secret e do salt que estão no banco de dados com o id "keyId";
            // TODO: 2. Construir uma nova cifra usando os dados do banco
            // TODO: 3. Armazenar essa cifra no objeto que possui todas as cifras
        }
        return selectedCipher;
    }

//    public static void main(String[] args) throws Exception {
//        String input = "There was a crooked man"; 
//        
//        String finalEncripted = generateFinalEncriptedData(input);
//        System.out.println(finalEncripted);
//        String finalDecriptedData = generateFinalDEncriptedData(finalEncripted);
//        System.out.println(finalDecriptedData);
//        
//    }

    private static String createFinalHashEncripted(String id, String encriptedStr) throws UnsupportedEncodingException {
        String finalEncoded = encodeToBase64(id + DEFAULT_ID_CRIPT_SEPARATOR + encriptedStr);
        return finalEncoded;
    }
    
    private static String decodeFromBase64(String myStr) throws UnsupportedEncodingException {
        return new String(Base64.getDecoder().decode(myStr));
    }

    private static String encodeToBase64(String myStr) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(myStr.getBytes("UTF-8"));
    }

//
}