package com.itau.latam.keystore.service.impl;


import java.util.List;

import java.security.SecureRandom;
import java.util.Base64;

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
	
	KeyGenerator keyGenerator = null;
	
    SecretKey key = null;

    // Generating IV.
    byte[] IV = new byte[16];
    private SecureRandom random = new SecureRandom();
    
	@Override
	public List<CompleteDataDTO> encryptData(List<CompleteDataDTO> completeDataDTO) {
		try
	    {    
			 keyGenerator = KeyGenerator.getInstance("AES");
			 keyGenerator.init(256);
			 key = keyGenerator.generateKey();
			 random.nextBytes(IV);
				
			 completeDataDTO.stream().forEach(c -> 
		          {
					try {
						c.setEncryptted(Base64.getEncoder().encodeToString(encrypt(c.getPlaintext().getBytes(),key,IV )));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			
		     );
	        return completeDataDTO;
	    } 
	    catch (Exception e) 
	    {
	        System.out.println("Error while encrypting: " + e.toString());
	        return null;
	    }
	    
	}

	@Override
	public List<CompleteDataDTO> decryptData(List<CompleteDataDTO> completeDataDTO) {
		try
	    {
			completeDataDTO.stream().forEach(c -> 
	          {
				try {
					c.setPlaintext(decrypt(Base64.getDecoder().decode(c.getEncryptted()),key, IV));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
	     );
      return completeDataDTO;

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
