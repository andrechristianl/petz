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

import com.petz.code.model.dto.ClientDTO;
import com.petz.code.service.impl.ClientServiceImpl;

@RestController
@RequestMapping(value = "/cad")
public class ClientController {
	
	@Autowired
	private ClientServiceImpl petzService;
	
	@ResponseBody
	@RequestMapping(value = "/client", method = POST)
	public void client(@RequestBody ClientDTO clientDTO) throws ConstraintViolationException, ServiceException{
		 petzService.clientAddListData(clientDTO); 
	}
	
	@ResponseBody
	@RequestMapping(value = "/client/{entryId}", method = PUT)
	public void updateClient(@PathVariable int entryId, @RequestBody ClientDTO clientDTO) throws ConstraintViolationException, ServiceException{
		 petzService.updateClient(entryId,clientDTO); 
	}
	
	@ResponseBody
	@RequestMapping(value = "/client/{entryId}", method = GET)
	public ClientDTO findClient(@PathVariable int entryId) throws ConstraintViolationException, ServiceException{
		 return petzService.findClient(entryId); 
	}
	
	@ResponseBody
	@RequestMapping(value = "/client/", method = GET)
	public List<ClientDTO> findAllClient() throws ConstraintViolationException, ServiceException{
		 return petzService.findAllClient(); 
	}
}
