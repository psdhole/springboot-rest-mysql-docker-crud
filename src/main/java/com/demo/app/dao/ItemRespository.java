package com.demo.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.app.model.Item;

public interface ItemRespository extends JpaRepository<Item, Long> {
}
