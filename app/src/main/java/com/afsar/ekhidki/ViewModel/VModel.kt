package com.afsar.ekhidki.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afsar.ekhidki.Models.Category
import com.afsar.ekhidki.Models.Products
import com.afsar.ekhidki.Models.Utils
import kotlinx.coroutines.*
import java.lang.Exception

class VModel : ViewModel() {
    lateinit var categorylist: List<Category>
    private val cartData: MutableLiveData<List<Category>> by lazy {
        MutableLiveData<List<Category>>().also {
            loadCart()
        }
    }

    private val productsData: MutableLiveData<List<Products>> by lazy {
        MutableLiveData<List<Products>>().also {
            loadProducts()
        }
    }

    fun getCartData(data: List<Category>): LiveData<List<Category>> {
        categorylist = data
        return cartData
    }

    fun getProducts(): LiveData<List<Products>> {
        return productsData
    }

    private fun loadCart() {
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                try {
                    cartData.value = categorylist
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun loadProducts() {
        val service = Utils.retrofit.create(Api::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val call = service.getProducts()
            withContext(Dispatchers.Main) {
                try {
                    if (call.isSuccessful) {
                        productsData.value = call.body()
                    } else {
                        Log.d("error", "occurred")
                    }
                } catch (e: Exception) {
                    Log.d("error", e.toString())
                }
            }
        }
    }
}