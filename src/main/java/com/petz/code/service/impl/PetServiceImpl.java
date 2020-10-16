package com.petz.code.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.petz.code.core.utils.ObjectDataConverterUtil;
import com.petz.code.model.dto.PetDTO;
import com.petz.code.repository.PetRepository;
import com.petz.code.repository.entity.Pet;
import com.petz.code.service.PetService;
import com.petz.code.validation.PetValidation;

@Service
public class PetServiceImpl implements PetService {
	@Autowired
	private PetRepository petRepository;

	@Override
	public void petAddListData(PetDTO petDTO) {
		PetValidation.validateRules(petDTO);
		Pet pet = ObjectDataConverterUtil.copyObject(petDTO, Pet.class);
		this.petRepository.save(pet);
	}

	@Override
	public void updatePet(int entryId, PetDTO petDTO) {
		PetValidation.validateRules(petDTO);
		petDTO.setId(entryId);
		Pet pet = ObjectDataConverterUtil.copyObject(petDTO, Pet.class);
		this.petRepository.save(pet);
	}

	@Override
	public PetDTO findPet(int entryId) {
		Pet pet = this.petRepository.findById(entryId);
		PetDTO petDTO = ObjectDataConverterUtil.copyObject(pet, PetDTO.class);
		return petDTO;
	}

	@Override
	public List<PetDTO> findAllPet() {
		List<Pet> pet = this.petRepository.findAll();
		List<PetDTO> petDTO = ObjectDataConverterUtil.copyList(pet, PetDTO.class);
		return petDTO;
	}

}
