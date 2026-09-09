package com.manning.javapersistence.ch08.kotlin.repository

import com.manning.javapersistence.ch08.kotlin.model.Item
import org.springframework.data.jpa.repository.JpaRepository

interface ItemRepository : JpaRepository<Item, Long>
