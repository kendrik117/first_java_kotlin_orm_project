package com.manning.javapersistence.ch08.kotlin.model

import java.math.BigDecimal
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
class Bid(
    @field:NotNull var amount: BigDecimal? = null,
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "ITEM_ID", nullable = false)
    var item: Item? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    override fun toString() = "Bid{id=$id, amount=$amount, itemId=${item?.id}}"
}
