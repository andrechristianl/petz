package com.itau.latam.keystore.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HealthCheckController {
	@ResponseBody
	@RequestMapping(value = "/healthcheck", method = GET)
	public void healthCheckEncrypt() throws ConstraintViolationException, ServiceException{
				
	}
}