package com.petz.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petz.code.repository.entity.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer>{
	Pet findById(int id);

}
