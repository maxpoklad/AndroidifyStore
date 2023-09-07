package com.poklad.androidifystore.data.remote.model

import com.google.gson.annotations.SerializedName

enum class ProductCategoryTypeNameResponse(val rawValue: String) {
    @SerializedName("electronics")
    ELECTRONICS("electronics"),

    @SerializedName("jewelery")
    JEWELRY("jewelery"),

    @SerializedName("men's clothing")
    MENS_CLOTHING("men's clothing"),

    @SerializedName("women's clothing")
    WOMENS_CLOTHING("women's clothing"),
}