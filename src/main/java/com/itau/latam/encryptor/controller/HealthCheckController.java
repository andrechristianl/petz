package com.itau.latam.encryptor.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HealthCheckController {
	@ResponseBody
	@RequestMapping(value = "/healthcheck", method = GET)
	public void healthCheck(){
	}
}
