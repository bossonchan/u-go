package com.ugo.server.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ugo.server.domain.entity.Item;
import com.ugo.server.domain.entity.Status;

public interface ItemRepository extends JpaRepository<Item, Long>{

	@Query(value = 
			"select i from Item i "
			+ "left join i.seller s "
			+ "where s.id = :sellerId "
      + "and i.status = 'ACTIVE'")
	List<Item> findItemsPostedBySeller(@Param("sellerId") Long sellerId);

	@Query(value = 
			"select i from Item i "
			+ "where i.status = 'ACTIVE'")
	List<Item> findActiveItems();

	@Query(value =
			"select i from Item i "
			+ "where i.status = 'ACTIVE' "
			+ "and i.name like %:search%")
	List<Item> findActiveItemsBySearch(@Param("search") String search);

}
