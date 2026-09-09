package com.manning.javapersistence.ch08.java.repository;

import com.manning.javapersistence.ch08.java.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
