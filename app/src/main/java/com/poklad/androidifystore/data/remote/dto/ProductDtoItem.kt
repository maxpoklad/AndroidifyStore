package com.poklad.androidifystore.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ProductDtoItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("price")
    val price: String,
)