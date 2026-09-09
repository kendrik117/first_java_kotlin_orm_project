package com.manning.javapersistence.ch08.kotlin

import com.manning.javapersistence.ch08.kotlin.configuration.SpringDataConfigurationKotlin
import com.manning.javapersistence.ch08.kotlin.model.Bid
import com.manning.javapersistence.ch08.kotlin.model.Item
import com.manning.javapersistence.ch08.kotlin.repository.BidRepository
import com.manning.javapersistence.ch08.kotlin.repository.ItemRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import java.math.BigDecimal

@SpringJUnitConfig(SpringDataConfigurationKotlin::class)
class MappingAssociationsSpringDataJPATest_Kotlin {
    @Autowired
    private lateinit var itemRepository: ItemRepository
    @Autowired
    private lateinit var bidRepository: BidRepository

    @Test
    fun storeLoadEntities() {
        val item = Item("Foo")
        val bid = Bid(BigDecimal.valueOf(100), item)
        val bid2 = Bid(BigDecimal.valueOf(200), item)
        itemRepository.save(item)
        item.addBid(bid)
        item.addBid(bid2)
        bidRepository.save(bid)
        bidRepository.save(bid2)
        assertEquals(1, itemRepository.findAll().size)
        assertEquals(2, bidRepository.findByItem(item).size)
    }
}
