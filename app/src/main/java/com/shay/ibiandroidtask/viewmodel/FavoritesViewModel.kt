package com.shay.ibiandroidtask.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.shay.ibiandroidtask.data.Graph
import com.shay.ibiandroidtask.data.ProductRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.shay.ibiandroidtask.data.ProductFavorite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FavoritesViewModel(private val productRepository: ProductRepository = Graph.productRepository) :
    ViewModel() {

    var productTitleState by mutableStateOf("")
    var productDescription by mutableStateOf("")

    lateinit var getAllProducts: Flow<List<ProductFavorite>>

    init {
        viewModelScope.launch{
            getAllProducts = productRepository.getProducts()
        }
    }

//    Add / Edit / Delete / Update favorite product

    fun addProduct(productFavorite: ProductFavorite){
        viewModelScope.launch{
            productRepository.addProduct(productFavorite)
        }
    }

    fun getProductById(id: Int): Flow<ProductFavorite>{
        return  productRepository.getProductById(id)
    }

    fun updateProduct(productFavorite: ProductFavorite){
        viewModelScope.launch(Dispatchers.IO){
            productRepository.updateProduct(productFavorite)
        }
    }

    fun deleteProduct(productFavorite: ProductFavorite){
        viewModelScope.launch(Dispatchers.IO){
            productRepository.deleteProduct(productFavorite)
        }
    }
}