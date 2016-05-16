package com.ugo.server.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ugo.server.domain.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

}
