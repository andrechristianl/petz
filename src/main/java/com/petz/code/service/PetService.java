package com.petz.code.service;


import java.util.List;

import com.petz.code.model.dto.PetDTO;

public interface PetService {

	void petAddListData(PetDTO petDTO);

	void updatePet(int entryId, PetDTO petDTO);

	PetDTO findPet(int entryId);

	List<PetDTO> findAllPet();
	
}
