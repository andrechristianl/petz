package com.itau.latam.keystore.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itau.latam.keystore.model.dto.CompleteDataDTO;
import com.itau.latam.keystore.service.impl.KeyStoreServiceImpl;

@RestController
@RequestMapping(value = "/ppid")
public class EncriptionDataController {
	
	@Autowired
	private KeyStoreServiceImpl keyStoreService;
	
	@ResponseBody
	@RequestMapping(value = "/encrypt", method = POST)
	public List<CompleteDataDTO> encrypt(@RequestBody List<CompleteDataDTO> completeData) throws ConstraintViolationException, ServiceException{
		//TODO: Fazer validação da entrada da String de texto p/
		//      o Google analytics (estimado máximo 56 caracteres)
		return keyStoreService.encryptListData(completeData); 
	}
	
	@ResponseBody
	@RequestMapping(value = "/decrypt", method = POST)
	public List<CompleteDataDTO> decrypt(@RequestBody List<CompleteDataDTO> completeData) throws ConstraintViolationException, ServiceException{
		//TODO: Fazer validação da entrada da String de texto p/
		//      o Google analytics (estimado máximo 125 caracteres)
		return keyStoreService.decryptListData(completeData); 
	}

	

}
