package com.shay.ibiandroidtask.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shay.ibiandroidtask.model.Product
import com.shay.ibiandroidtask.productService
import kotlinx.coroutines.launch

class ProductsViewModel: ViewModel() {

    private val _productsState = mutableStateOf(ProductsState())
    val productsState: State<ProductsState> = _productsState

    init {
        fetchProducts()
    }

    private fun fetchProducts(){
        viewModelScope.launch{
            try {
                var response = productService.getProducts()
                _productsState.value = _productsState.value.copy(
                    list = response.products,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _productsState.value = _productsState.value.copy(
                    loading = false,
                    error = "Error fetching Products ${e.message}"
                )
            }
        }
    }

    data class ProductsState(
        val loading: Boolean = true,
        val list: List<Product> = emptyList(),
        val error: String? = null
    )
}

