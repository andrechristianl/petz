package com.itau.latam.keystore.cipher.util;

import static com.itau.latam.keystore.cipher.util.EncodingUtil.decodeFromBase64;
import static com.itau.latam.keystore.cipher.util.EncodingUtil.encodeToBase64;

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

public class CipherSuiteManager {
	
    static final String DEFAULT_ID_CRIPT_SEPARATOR = ".";

	private static Map<String, CipherSuite> ciphers;

	static {
		ciphers = new HashMap<String, CipherSuite>();
	}

	static synchronized void validateCipherSuite(String secretKey, String salt, String id) {
		
		CipherSuite selectedCipher = ciphers.get(id);
		if (selectedCipher == null) {
			CipherSuite c = null;
			try {
				c = new CipherSuite(secretKey, salt);
			} catch (NullPointerException | NoSuchAlgorithmException | InvalidKeySpecException
					| NoSuchPaddingException e) {
				throw new RuntimeException("There has been an issue while trying to initialize the Cipher");
			}
			AESCBC256.LAST_ID = id;
			ciphers.put(id, c);
		}
		
		
	}

	private static CipherSuite findCipherById(String id) {
		CipherSuite selectedCipher = id == null ? null : ciphers.get(id);
		if (selectedCipher == null) {
			throw new RuntimeException("There has been an issue while trying to retrieve the Cipher");
		}
		return selectedCipher;
	}

	static String encrypt(String stringToEncrypt) throws InvalidKeyException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		String finalStr = "";
		CipherSuite cipherSuite = findCipherById(AESCBC256.LAST_ID);
		Cipher cipher = cipherSuite.getCipher();
		synchronized (cipher) {
			cipher.init(Cipher.ENCRYPT_MODE, cipherSuite.getSecretKey(), cipherSuite.getIvspec());
			finalStr = Base64.getEncoder()
					.encodeToString(cipher.doFinal(stringToEncrypt.getBytes(StandardCharsets.UTF_8.name())));
		}
		return finalStr;
	}

	static String decrypt(String stringToDecrypt, String keyId) throws InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		CipherSuite cipherSuite = findCipherById(keyId);
		Cipher cipher = cipherSuite.getCipher();
		cipher.init(Cipher.DECRYPT_MODE, cipherSuite.getSecretKey(), cipherSuite.getIvspec());
		return new String(cipher.doFinal(Base64.getDecoder().decode(stringToDecrypt)));
	}

	static String[] generateComposedKey(String encodedString) {
		return decodeFromBase64(encodedString).split("\\" + DEFAULT_ID_CRIPT_SEPARATOR, 2);
	}
	
	
    static String createFinalHashEncripted(String id, String encriptedString) throws UnsupportedEncodingException {
        return encodeToBase64(id + CipherSuiteManager.DEFAULT_ID_CRIPT_SEPARATOR + encriptedString);
    }
}
