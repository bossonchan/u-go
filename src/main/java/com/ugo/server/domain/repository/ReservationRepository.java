package com.ugo.server.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ugo.server.domain.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

}
