package com.poklad.androidifystore.presentation.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class ProductItemUi(
    val id: Int,
    val title: String,
    val category: String,
    val description: String,
    val image: String,
    val price: String,
) : Parcelable