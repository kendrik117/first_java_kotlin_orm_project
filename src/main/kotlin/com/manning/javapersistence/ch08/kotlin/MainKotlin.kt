package com.manning.javapersistence.ch08.kotlin

import com.manning.javapersistence.ch08.kotlin.configuration.SpringDataConfigurationKotlin
import com.manning.javapersistence.ch08.kotlin.model.Bid
import com.manning.javapersistence.ch08.kotlin.model.Item
import com.manning.javapersistence.ch08.kotlin.repository.BidRepository
import com.manning.javapersistence.ch08.kotlin.repository.ItemRepository
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.math.BigDecimal

fun main() {
    System.setProperty("app.db.suffix", "V_Main")
    AnnotationConfigApplicationContext(SpringDataConfigurationKotlin::class.java).use { context ->
        val itemRepository = context.getBean(ItemRepository::class.java)
        val bidRepository = context.getBean(BidRepository::class.java)
        val item = Item("Foo")
        val bid = Bid(BigDecimal.valueOf(100), item)
        val bid2 = Bid(BigDecimal.valueOf(200), item)
        itemRepository.save(item)
        item.addBid(bid)
        item.addBid(bid2)
        bidRepository.save(bid)
        bidRepository.save(bid2)
        println(itemRepository.findById(item.id!!).orElseThrow())
        bidRepository.findByItem(item).forEach(::println)
    }
}
