package com.petz.code.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.petz.code.model.dto.PetDTO;
import com.petz.code.service.impl.PetServiceImpl;

@RestController
@RequestMapping(value = "/cad")
public class PetController {
	
	@Autowired
	private PetServiceImpl petzService;
	
	@ResponseBody
	@RequestMapping(value = "/pet", method = POST)
	public void pet(@RequestBody PetDTO petDTO) throws ConstraintViolationException, ServiceException{
		 petzService.petAddListData(petDTO); 
	}
	
	@ResponseBody
	@RequestMapping(value = "/pet/{entryId}", method = PUT)
	public void updatePet(@PathVariable int entryId, @RequestBody PetDTO petDTO) throws ConstraintViolationException, ServiceException{
		 petzService.updatePet(entryId,petDTO); 
	}
	
	@ResponseBody
	@RequestMapping(value = "/pet/{entryId}", method = GET)
	public PetDTO findPet(@PathVariable int entryId) throws ConstraintViolationException, ServiceException{
		 return petzService.findPet(entryId); 
	}
	
	@ResponseBody
	@RequestMapping(value = "/pet/", method = GET)
	public List<PetDTO> findAllPet() throws ConstraintViolationException, ServiceException{
		 return petzService.findAllPet(); 
	}
}
