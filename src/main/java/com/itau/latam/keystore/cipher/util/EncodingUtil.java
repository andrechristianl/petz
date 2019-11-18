package com.itau.latam.keystore.cipher.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncodingUtil {
    private static final String DEFAULT_ID_CRIPT_SEPARATOR = ".";
    
    public static String findIdKeyFromEncriptedData(String encriptedString){
        return decodeFromBase64(generateComposedKey(encriptedString)[0]);
    }

    private static String[] generateComposedKey(String encodedString) {
        return decodeFromBase64(encodedString).split("\\" + DEFAULT_ID_CRIPT_SEPARATOR, 2);
    }
    
    public static String decodeFromBase64(String myStr) {
        return new String(Base64.getDecoder().decode(myStr));
    }

    public static String encodeToBase64(String myStr) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(myStr.getBytes(StandardCharsets.UTF_8.name()));
    }
}
