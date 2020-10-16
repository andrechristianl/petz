package com.petz.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petz.code.repository.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{
	Client findById(int id);

}
