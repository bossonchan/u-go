package com.ugo.server.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ugo.server.domain.entity.ShoppingCartItem;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long>{

}
