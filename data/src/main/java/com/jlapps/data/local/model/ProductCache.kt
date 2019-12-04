package com.jlapps.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductCache(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val name: String = "",
    val imageURL: String = "",
    val description: String = ""
)

