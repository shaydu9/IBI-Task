package com.shay.ibiandroidtask

import com.shay.ibiandroidtask.model.ProductsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val retrofit = Retrofit.Builder().baseUrl("https://dummyjson.com")
    .addConverterFactory(GsonConverterFactory.create()).build()

val productService = retrofit.create(ApiService::class.java)

interface ApiService {

    @GET("products")
    suspend fun getProducts(): ProductsResponse
}