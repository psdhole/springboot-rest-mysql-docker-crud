package com.demo.app.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.app.model.Item;
import com.demo.app.services.ItemService;

@RestController
@RequestMapping("/api/v1/items")
public class ItemAPI {

	@Autowired
	private ItemService itemService;

	private static Logger log = LoggerFactory.getLogger(ItemAPI.class);

	@GetMapping
	public ResponseEntity<List<Item>> findAll() {
		return ResponseEntity.ok(itemService.findAll());
	}

	@PostMapping
	public ResponseEntity<Item> create(@Valid @RequestBody Item item) {
		System.out.println("item : " + item);
		return ResponseEntity.ok(itemService.save(item));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Item> findById(@PathVariable Long id) {
		Optional<Item> stock = itemService.findById(id);
		if (!stock.isPresent()) {
			log.error("Id " + id + " is not existed");
			ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(stock.get());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Item> update(@PathVariable Long id, @Valid @RequestBody Item item) {
		if (!itemService.findById(id).isPresent()) {
			log.error("Id " + id + " is not existed");
			ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(itemService.save(item));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		if (!itemService.findById(id).isPresent()) {
			log.error("Id " + id + " is not existed");
			ResponseEntity.badRequest().build();
		}
		itemService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
