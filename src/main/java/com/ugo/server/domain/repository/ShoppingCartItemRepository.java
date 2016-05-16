package com.ugo.server.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ugo.server.domain.entity.ShoppingCartItem;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long>{

	@Query(value = 
			"select s from ShoppingCartItem s "
			+ "left join s.buyer b "
			+ "where b.id = :buyerId "
			+ "and s.status = 'ACTIVE' ")
	List<ShoppingCartItem> findActiveItemsByBuyerId(@Param("buyerId") Long buyerId);
	

}
