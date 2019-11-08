package com.itau.latam.keystore.service.impl;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import com.itau.latam.keystore.model.dto.CompleteDataDTO;
import com.itau.latam.keystore.service.CompleteDataService;

@Service
public class CompleteDataServiceImpl implements CompleteDataService{
	
	
	@Override
	public List<CompleteDataDTO> encryptData(CompleteDataDTO completeDataDTO) {
		try
	    {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(256);
			// Generating IV.
	        byte[] IV = new byte[16];
	        SecureRandom random = new SecureRandom();
	        random.nextBytes(IV);
	        completeDataDTO.setEncryptted(Base64.getEncoder().
		                          encodeToString(encrypt(completeDataDTO.getPlaintext().getBytes(),
     		                                     keyGenerator.generateKey(), IV)));
	        return Arrays.asList(completeDataDTO);
	    } 
	    catch (Exception e) 
	    {
	        System.out.println("Error while encrypting: " + e.toString());
	        return null;
	    }
	    
	}

	@Override
	public List<CompleteDataDTO> decryptData(CompleteDataDTO completeDataDTO) {
		try
	    {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(256);
			 // Generating IV.
	        byte[] IV = new byte[16];
	        SecureRandom random = new SecureRandom();
	        random.nextBytes(IV);
	        completeDataDTO.setPlaintext(decrypt(completeDataDTO.getEncryptted().getBytes(),keyGenerator.generateKey(), IV));
	        return Arrays.asList(completeDataDTO);
	    } 
	    catch (Exception e) 
	    {
	        System.out.println("Error while encrypting: " + e.toString());
	        return null;
	    }
	}
	
	public static byte[] encrypt (byte[] plaintext,SecretKey key,byte[] IV ) throws Exception
    {
        //Get Cipher Instance
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        
        //Create SecretKeySpec
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
        
        //Create IvParameterSpec
        IvParameterSpec ivSpec = new IvParameterSpec(IV);
        
        //Initialize Cipher for ENCRYPT_MODE
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        
        //Perform Encryption
        byte[] cipherText = cipher.doFinal(plaintext);
        
        return cipherText;
    }
	
    public static String decrypt (byte[] cipherText, SecretKey key,byte[] IV) throws Exception
    {
        //Get Cipher Instance
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        
        //Create SecretKeySpec
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
        
        //Create IvParameterSpec
        IvParameterSpec ivSpec = new IvParameterSpec(IV);
        
        //Initialize Cipher for DECRYPT_MODE
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        
        //Perform Decryption
        byte[] decryptedText = cipher.doFinal(cipherText);
        
        return new String(decryptedText);
    }
}
