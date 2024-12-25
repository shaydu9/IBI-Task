package com.shay.ibiandroidtask.data

import kotlinx.coroutines.flow.Flow

class ProductRepository(private val productDao: ProductDao) {

    suspend fun addProduct(productFavorite: ProductFavorite){
        productDao.addProduct(productFavorite)
    }

    fun getProducts(): Flow<List<ProductFavorite>> = productDao.getAllFavorites()

    fun getProductById(id: Int): Flow<ProductFavorite> {
        return productDao.getProductById(id)
    }

    suspend fun updateProduct(productFavorite: ProductFavorite){
        productDao.updateProduct(productFavorite)
    }

    suspend fun deleteProduct(productFavorite: ProductFavorite){
        productDao.deleteProduct(productFavorite)
    }
}