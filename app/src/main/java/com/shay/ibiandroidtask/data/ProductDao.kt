package com.shay.ibiandroidtask.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ProductDao {

    @Insert(onConflict = OnConflictStrategy.Companion.IGNORE)
    abstract suspend fun addProduct(productEntity: ProductFavorite)

    @Query("Select * From 'favorites-table'")
    abstract fun getAllFavorites(): Flow<List<ProductFavorite>>

    @Update
    abstract suspend fun updateProduct(productEntity: ProductFavorite)

    @Delete
    abstract suspend fun deleteProduct(productEntity: ProductFavorite)

    @Query("Select * from `favorites-table` where id=:id")
    abstract fun getProductById(id: Int): Flow<ProductFavorite>
}