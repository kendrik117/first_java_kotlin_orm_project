package com.manning.javapersistence.ch08.java;

import com.manning.javapersistence.ch08.java.configuration.SpringDataConfigurationJava;
import com.manning.javapersistence.ch08.java.model.Bid;
import com.manning.javapersistence.ch08.java.model.Item;
import com.manning.javapersistence.ch08.java.repository.BidRepository;
import com.manning.javapersistence.ch08.java.repository.ItemRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class MainJava {
    public static void main(String[] args) {
        System.setProperty("app.db.suffix", "V_Main");
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(SpringDataConfigurationJava.class)) {
            ItemRepository itemRepository = context.getBean(ItemRepository.class);
            BidRepository bidRepository = context.getBean(BidRepository.class);

            Item item = new Item("Foo");
            Bid bid = new Bid(BigDecimal.valueOf(100), item);
            Bid bid2 = new Bid(BigDecimal.valueOf(200), item);
            itemRepository.save(item);
            item.addBid(bid);
            item.addBid(bid2);
            bidRepository.save(bid);
            bidRepository.save(bid2);

            System.out.println(itemRepository.findById(item.getId()).orElseThrow());
            bidRepository.findByItem(item).forEach(System.out::println);
        }
    }
}
