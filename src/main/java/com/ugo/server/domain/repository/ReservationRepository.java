package com.ugo.server.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ugo.server.domain.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

	@Query(value = 
			"select r from Reservation r "
			+ "left join r.seller s "
			+ "where s.id = :sellerId "
			+ "and r.status != 'INACTIVE' "
      + "and r.status != 'REMOVED'")
	List<Reservation> findReservationsSentToSeller(@Param("sellerId") Long sellerId);

	@Query(value = 
			"select r from Reservation r "
			+ "left join r.buyer b "
			+ "where b.id = :buyerId "
			+ "and r.status != 'REMOVED'")
	List<Reservation> findReservationsCreatedByBuyer(@Param("buyerId") Long buyerId);

}
