package com.itau.latam.encryptor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itau.latam.encryptor.repository.entity.Encryptor;

public interface EncryptorRepository extends JpaRepository<Encryptor, Integer>{
	List<Encryptor> findBydateCreated();
	
	List<Encryptor> findById(int id);
}
