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
public class HealthCheckController {
	@Autowired
	CompleteDataService completeDataService;
	String checkIn = new String();
	String checkOut = new String();
	@ResponseBody
	@RequestMapping(value = "/healthcheck", method = POST)
	public String healthCheckEncrypt(@RequestBody List <CompleteDataDTO> completeDataDTO) throws ConstraintViolationException, ServiceException{
		completeDataService.encryptData(completeDataDTO);
		completeDataDTO.stream().forEach(c -> {
			checkIn = c.getPlaintext();
			System.out.println("In: " + checkIn);
		});
		completeDataService.decryptData(completeDataDTO);
		completeDataDTO.stream().forEach(c -> {
			checkOut = c.getPlaintext();
			System.out.println("Out: " + checkOut);
		});

		if(checkIn.equals(checkOut)) {
			return "OK";
		}else {
			return "Error encrypting/Decrypting";
		}
	}
}
