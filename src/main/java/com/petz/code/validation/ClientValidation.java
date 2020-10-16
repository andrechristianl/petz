package com.petz.code.validation;

import org.hibernate.service.spi.ServiceException;

import com.petz.code.model.dto.ClientDTO;


public class ClientValidation {
	
	public static void validateRules(ClientDTO clientDTO) throws ServiceException {
		if (clientDTO == null || clientDTO.getName() == null || clientDTO.getName().isEmpty()) {
			throw new ServiceException("Malformed request body. There must be a 'name' field and it cannot be null or empty");
		}else if (clientDTO == null || clientDTO.getAddress() == null || clientDTO.getAddress().isEmpty()) {
			throw new ServiceException("Malformed request body. There must be a 'address' field and it cannot be null or empty");
		}else if (clientDTO == null || clientDTO.getDistrict() == null || clientDTO.getDistrict().isEmpty()) {
			throw new ServiceException("Malformed request body. There must be a 'district' field and it cannot be null or empty");
		}else if (clientDTO == null || clientDTO.getUf() == null || clientDTO.getUf().isEmpty()) {
			throw new ServiceException("Malformed request body. There must be a 'Uf' field and it cannot be null or empty");
		}else if (clientDTO == null || clientDTO.getPhone() == null || clientDTO.getPhone().isEmpty()) {
			throw new ServiceException("Malformed request body. There must be a 'phone' field and it cannot be null or empty");
		}else if (clientDTO == null || clientDTO.getEmail() == null || clientDTO.getEmail().isEmpty()) {
			throw new ServiceException("Malformed request body. There must be a 'email' field and it cannot be null or empty");
		}
		
	}

}
