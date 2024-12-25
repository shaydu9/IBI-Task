package com.shay.ibiandroidtask.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shay.ibiandroidtask.model.Product

@Entity(tableName = "favorites-table")
data class ProductFavorite(
    @PrimaryKey(true)
    val id: Int = 0,
    @ColumnInfo("product-title")
    val title: String,
    @ColumnInfo("product-desc")
    val description: String,
    @ColumnInfo("product-price")
    val price: Double,
    @ColumnInfo("product-brand")
    val brand: String? = null,
    @ColumnInfo("product-thumb")
    val thumbnail: String,
//    @ColumnInfo("product-images")
//    val images: ArrayList<String> = arrayListOf()
)
