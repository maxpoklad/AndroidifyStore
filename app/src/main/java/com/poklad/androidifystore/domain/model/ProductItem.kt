package com.poklad.androidifystore.domain.model

data class ProductItem(
    val id: Int,
    val title: String,
    val category: String,
    val description: String,
    val image: String,
    val price: String,
)