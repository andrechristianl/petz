package com.itau.latam.keystore.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public final class AESCBC256 {
    private static byte[] iv = { 1, 2, 3, 3, 3, 4, 5, 6, 8, 9, 0, 2, 0, 1, 0, 1 };
    private static IvParameterSpec ivspec = new IvParameterSpec(iv);
    
    private static String secretKeyStr = "aSDG5343F24F5THRTU7trj57wbwbdhnngfdghdtf3FdDedss34DfgMjKiKij3kjd";
    private static String salt = "MmskKjjhuiop97654tgGghjklskskjhG54rfgghbNhgbhNjhjUjhYhgTfDvnMmhGf";
    
    private static String algorithm = "AES";
    private static String secretKeyInstance = "PBKDF2WithHmacSHA256";
    private static String transformation = "AES/CBC/PKCS5Padding";
   
    public static String encrypt(String clearText) throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException {
        SecretKeySpec secretKey = getSecretKey();
        Cipher cipher = getCipherInstance();
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
        return createUTF8String(cipher.doFinal(clearText.getBytes(StandardCharsets.UTF_8.name())));
    }

    public static String decrypt(String decodedByteArray) throws InvalidKeyException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException {
        SecretKeySpec secretKey = getSecretKey();
        Cipher cipher = getCipherInstance();
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
        return createUTF8String(cipher.doFinal(decodedByteArray.getBytes()));
    }
    
    public static String encodeCustomHash(String id, byte[] encryptedByteArray) throws UnsupportedEncodingException {
        return encodeBase64(encodeBase64(id).concat(".").concat(createUTF8String(encryptedByteArray)));
    }
    
    public static String[] decodeCustomHash(String input) throws UnsupportedEncodingException {
        String[] array = decodeBase64(input).split("\\.");
        String[] decodedArray= {decodeBase64(array[0]), array[1]};
        return decodedArray;
    }
    
    private static SecretKeySpec getSecretKey() throws InvalidKeySpecException, NoSuchAlgorithmException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance(secretKeyInstance);
        KeySpec spec = new PBEKeySpec(secretKeyStr.toCharArray(), salt.getBytes(), 65536, 256);
        return new SecretKeySpec(factory.generateSecret(spec).getEncoded(), algorithm);
    }
    
    private static Cipher getCipherInstance() throws NoSuchAlgorithmException, NoSuchPaddingException {
        return Cipher.getInstance(transformation);
    }

    private static String createUTF8String(byte[] byteArray) throws UnsupportedEncodingException {
        return new String (byteArray, StandardCharsets.UTF_8.name());
    }

    private static String encodeBase64(String inputString) throws UnsupportedEncodingException {
        return new String (Base64.getEncoder().encode(inputString.getBytes()), StandardCharsets.UTF_8.name());
    }

    private static String decodeBase64(String encodedBase64) throws UnsupportedEncodingException {
        return createUTF8String(Base64.getDecoder().decode(encodedBase64));
    }
}
