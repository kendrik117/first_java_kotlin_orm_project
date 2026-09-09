package com.manning.javapersistence.ch08.kotlin.model

import javax.persistence.*

@Entity
class Item(var name: String? = null) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    private val bids: MutableSet<Bid> = LinkedHashSet()

    fun getBids(): Set<Bid> = bids.toSet()

    fun addBid(bid: Bid) {
        bids.add(bid)
    }

    override fun toString() = "Item{id=$id, name='$name'}"
}
