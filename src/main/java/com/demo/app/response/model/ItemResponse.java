package com.demo.app.response.model;

import java.util.List;

import com.demo.app.model.Item;

public class ItemResponse {
	private String message;
	private String status;
	private List<Item> items;
	private Item item;

	public ItemResponse() {
		super();
	}

	public ItemResponse(String message, String status, List<Item> items, Item item) {
		super();
		this.message = message;
		this.status = status;
		this.items = items;
		this.item = item;
	}

	public ItemResponse(String message, String status, List<Item> items) {
		super();
		this.message = message;
		this.status = status;
		this.items = items;
	}

	public ItemResponse(String message, String status, Item item) {
		super();
		this.message = message;
		this.status = status;
		this.item = item;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}
