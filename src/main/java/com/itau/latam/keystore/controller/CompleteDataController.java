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
import com.itau.latam.keystore.service.CompleteDataService;

@RestController
public class CompleteDataController {
	
	@Autowired
	private CompleteDataService completeDataService;
	
	@ResponseBody
	@RequestMapping(value = "/encrypt", method = POST)
	public List<CompleteDataDTO> encrypt(@RequestBody CompleteDataDTO completeData) throws ConstraintViolationException, ServiceException{
		return completeDataService.encrypt(completeData); 
	}
	
	@ResponseBody
	@RequestMapping(value = "/decrypt", method = POST)
	public List<CompleteDataDTO> decrypt(@RequestBody CompleteDataDTO completeData) throws ConstraintViolationException, ServiceException{
		return completeDataService.decrypt(completeData); 
	}

}
