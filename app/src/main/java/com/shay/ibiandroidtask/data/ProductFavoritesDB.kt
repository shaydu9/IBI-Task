package com.shay.ibiandroidtask.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ProductFavorite::class],
    version = 1,
    exportSchema = false
)

abstract class ProductFavoritesDB : RoomDatabase(){
    abstract fun productDao(): ProductDao
}