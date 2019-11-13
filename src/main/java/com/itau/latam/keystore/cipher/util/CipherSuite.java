package com.itau.latam.keystore.cipher.util;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class CipherSuite {    
    private static final String ALGORITHM = "AES";
    private static final String SECRET_KEY_FACTORY_TYPE = "PBKDF2WithHmacSHA256";
    private static final String CIPHER_TYPE = "AES/CBC/PKCS5Padding";
    private static final byte[] IV = { 1, 2, 3, 3, 3, 4, 5, 6, 8, 9, 0, 2, 0, 1, 0, 1 };

    private IvParameterSpec ivParameterSpec;
    private KeySpec keySpec;
    private SecretKey secretKey;
    private SecretKeySpec secretKeySpec;
    private Cipher cipher;

    public CipherSuite(String secretKeyStr, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException {
        initCipherSuite(secretKeyStr, salt);
    }

    public void initCipherSuite(String secretKeyStr, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException {
        this.ivParameterSpec = new IvParameterSpec(IV);
        this.keySpec = new PBEKeySpec(secretKeyStr.toCharArray(), salt.getBytes(), 65536, 256);
        this.secretKey = SecretKeyFactory.getInstance(SECRET_KEY_FACTORY_TYPE).generateSecret(this.keySpec);
        this.secretKeySpec = new SecretKeySpec(this.secretKey.getEncoded(), ALGORITHM);
        this.cipher = Cipher.getInstance(CIPHER_TYPE);
    }

    public Cipher getCipher() {
        return this.cipher;
    }

    public IvParameterSpec getIvspec() {
        return this.ivParameterSpec;
    }

    public SecretKeySpec getSecretKey() {
        return this.secretKeySpec;
    }
}