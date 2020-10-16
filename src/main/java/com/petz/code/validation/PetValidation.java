package com.petz.code.validation;

import org.hibernate.service.spi.ServiceException;

import com.petz.code.model.dto.PetDTO;

public class PetValidation {

	public static void validateRules(PetDTO petDTO) throws ServiceException {
		if (petDTO == null || petDTO.getName() == null || petDTO.getName().isEmpty()) {
			throw new ServiceException(
					"Malformed request body. There must be a 'name' field and it cannot be null or empty");
		} else if (petDTO == null || petDTO.getAge() == null || petDTO.getAge().isEmpty()) {
			throw new ServiceException(
					"Malformed request body. There must be a 'address' field and it cannot be null or empty");
		} else if (petDTO == null || petDTO.getBreed() == null || petDTO.getBreed().isEmpty()) {
			throw new ServiceException(
					"Malformed request body. There must be a 'district' field and it cannot be null or empty");
		} else if (petDTO == null || petDTO.getNote() == null || petDTO.getNote().isEmpty()) {
			throw new ServiceException(
					"Malformed request body. There must be a 'Uf' field and it cannot be null or empty");
		}
	}
}
