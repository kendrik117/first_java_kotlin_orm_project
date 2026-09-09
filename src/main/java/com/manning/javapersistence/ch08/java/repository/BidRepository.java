package com.manning.javapersistence.ch08.java.repository;

import com.manning.javapersistence.ch08.java.model.Bid;
import com.manning.javapersistence.ch08.java.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface BidRepository extends JpaRepository<Bid, Long> {
    Set<Bid> findByItem(Item item);
}
