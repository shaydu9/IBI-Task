package com.shay.ibiandroidtask.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product (
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val brand: String? = null,
    val thumbnail: String,
    val images: ArrayList<String> = arrayListOf(),
    var isFavorite: Boolean = false
) : Parcelable

data class ProductsResponse(val products: List<Product>)
