package com.demo.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.app.dao.ItemRespository;
import com.demo.app.model.Item;

@Service
public class ItemService {
	@Autowired
	private ItemRespository itemRespository;

	public List<Item> findAll() {
		return itemRespository.findAll();
	}

	public Optional<Item> findById(Long id) {
		return itemRespository.findById(id);
	}

	public Item save(Item item) {
		return itemRespository.save(item);
	}

	public void deleteById(Long id) {
		itemRespository.deleteById(id);
	}
}
