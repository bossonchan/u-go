package com.ugo.server.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ugo.server.domain.entity.Buyer;

public interface BuyerRepository extends JpaRepository<Buyer, Long>{
	Buyer findByUsername(String username);

}
