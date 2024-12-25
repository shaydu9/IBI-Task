package com.shay.ibiandroidtask.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room

object Graph {
    lateinit var database: ProductFavoritesDB

    val productRepository by lazy {
        ProductRepository(productDao = database.productDao())
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(context, ProductFavoritesDB::class.java, "products.db").build()
    }
}