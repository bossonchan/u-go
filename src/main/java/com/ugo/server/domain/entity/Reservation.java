package com.ugo.server.domain.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Reservation {
	@Id
	@GeneratedValue
	private Long id;
	
	private Date time;
	
	@Enumerated(value=EnumType.STRING)
	private Status status;
	
	@ManyToOne
	@JoinColumn(name = "buyerId")
	private Buyer buyer;
	
	@ManyToOne
	@JoinColumn(name = "sellerId")
	private Seller seller;
	
	@ManyToOne
	@JoinColumn(name = "itemId")
	private Item item;
	
	public Reservation() {}
	
	public Reservation(Buyer buyer, Item item) {
		this.time = new Date();
		this.status = Status.PENDDING;
		this.buyer = buyer;
		this.item = item;
		this.seller = item.getSeller();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
}
