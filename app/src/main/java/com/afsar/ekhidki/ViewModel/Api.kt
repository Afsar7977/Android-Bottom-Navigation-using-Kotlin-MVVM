package com.afsar.ekhidki.ViewModel

import com.afsar.ekhidki.Models.Products
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("json/get/bVUvMmopua?indent=3")
    suspend fun getProducts(): Response<List<Products>>
}