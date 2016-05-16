package com.ugo.server.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.ugo.server.domain.repository.ItemPhotoRepository;
import com.ugo.server.domain.repository.ItemRepository;
import com.ugo.server.domain.repository.ReservationRepository;
import com.ugo.server.domain.repository.ShoppingCartItemRepository;

public class ItemManager {
	final ItemRepository itemRepo;
	final ReservationRepository reservationRepo;
	final ItemPhotoRepository itemPhotoRepo;
	final ShoppingCartItemRepository shoppingCartItemRepo;
	
	@Autowired
	public ItemManager(
			ItemRepository itemRepo,
			ReservationRepository reservationRepo,
			ItemPhotoRepository itemPhotoRepo,
			ShoppingCartItemRepository shoppingCartItemRepo) {
		this.itemRepo = itemRepo;
		this.reservationRepo = reservationRepo;
		this.itemPhotoRepo = itemPhotoRepo;
		this.shoppingCartItemRepo = shoppingCartItemRepo;
	}
}
