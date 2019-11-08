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
	
	 
	@Override
	public List<CompleteDataDTO> encryptData(List<CompleteDataDTO> completeDataDTO) {
		try
	    {    	
			 completeDataDTO.stream().forEach(c -> 
		          {
					try {
						//Inserir chamada do serviço encriptador
						c.setEncryptted(c.getPlaintext());
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
					//Inserir chamada do serviço decryptador
					c.setPlaintext(c.getEncryptted());
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
}
