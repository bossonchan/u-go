package com.ugo.server.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugo.server.domain.entity.Buyer;
import com.ugo.server.domain.entity.Item;
import com.ugo.server.domain.entity.Reservation;
import com.ugo.server.domain.entity.Seller;
import com.ugo.server.domain.entity.ShoppingCartItem;
import com.ugo.server.domain.entity.Status;
import com.ugo.server.domain.repository.ItemPhotoRepository;
import com.ugo.server.domain.repository.ItemRepository;
import com.ugo.server.domain.repository.ReservationRepository;
import com.ugo.server.domain.repository.ShoppingCartItemRepository;

@Service
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

	public Item createItem(Seller seller, Item item) {
		item.setSeller(seller);
		item.setTime(new Date());
		item.setStatus(Status.ACTIVE);
		return this.itemRepo.save(item);
	}

	public Item findItemById(Long itemId) {
		return this.itemRepo.findOne(itemId);
	}

	public Reservation reserveItem(Buyer buyer, Item item) {
		Reservation reservation = new Reservation(buyer, item);
		return this.reservationRepo.save(reservation);
	}

	public Item removeItem(Item item) {
		item.setStatus(Status.INACTIVE);
		return this.itemRepo.save(item);
	}

	public List<Item> getActiveItems() {
		return this.itemRepo.findActiveItems();
	}

	public List<Item> getSellerItems(Seller seller) {
		return this.itemRepo.findItemsPostedBySeller(seller.getId());
	}

	public List<Reservation> getSellerReservations(Seller seller) {
		return this.reservationRepo.findReservationsSentToSeller(seller.getId());
	}

	public List<Reservation> getBuyerReservations(Buyer buyer) {
		return this.reservationRepo.findReservationsCreatedByBuyer(buyer.getId());
	}

	public Reservation findReservationById(Long reservationId) {
		return this.reservationRepo.findOne(reservationId);
	}

	public Reservation rejectReservation( Reservation reservation) {
		reservation.setStatus(Status.INACTIVE);
		return this.reservationRepo.save(reservation);
	}
	
	public Reservation removeReservation( Reservation reservation) {
		reservation.setStatus(Status.REMOVED);
		return this.reservationRepo.save(reservation);
	}

	public ShoppingCartItem addToShoppingCart(Buyer buyer, Item item) {
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem(buyer, item);
		return this.shoppingCartItemRepo.save(shoppingCartItem);
	}

	public List<ShoppingCartItem> getShoppingCartItems(Buyer buyer) {
		return this.shoppingCartItemRepo.findActiveItemsByBuyerId(buyer.getId());
	}

	public ShoppingCartItem findShoppingCartItemById(Long shoppingCartItemId) {
		return this.shoppingCartItemRepo.findOne(shoppingCartItemId);
	}

	public ShoppingCartItem removeShoppingCartItem(ShoppingCartItem shoppingCartItem) {
		shoppingCartItem.setStatus(Status.REMOVED);
		return this.shoppingCartItemRepo.save(shoppingCartItem);
	}

	public List<Item> getActiveItemsAccordingToSearch(String search) {
		return this.itemRepo.findActiveItemsBySearch(search);
	}
}
