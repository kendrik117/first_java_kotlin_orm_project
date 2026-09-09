package com.manning.javapersistence.ch08.java;

import com.manning.javapersistence.ch08.java.configuration.SpringDataConfigurationJava;
import com.manning.javapersistence.ch08.java.model.Bid;
import com.manning.javapersistence.ch08.java.model.Item;
import com.manning.javapersistence.ch08.java.repository.BidRepository;
import com.manning.javapersistence.ch08.java.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(SpringDataConfigurationJava.class)
class MappingAssociationsSpringDataJPATest_Java {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private BidRepository bidRepository;

    @Test
    void storeLoadEntities() {
        Item item = new Item("Foo");
        Bid bid = new Bid(BigDecimal.valueOf(100), item);
        Bid bid2 = new Bid(BigDecimal.valueOf(200), item);
        itemRepository.save(item);
        item.addBid(bid);
        item.addBid(bid2);
        bidRepository.save(bid);
        bidRepository.save(bid2);
        assertEquals(1, itemRepository.findAll().size());
        assertEquals(2, bidRepository.findByItem(item).size());
    }
}
