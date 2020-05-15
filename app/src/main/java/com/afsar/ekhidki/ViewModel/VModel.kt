package com.afsar.ekhidki.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.afsar.ekhidki.Models.Products
import com.afsar.ekhidki.Models.Utils
import com.afsar.ekhidki.Room.AppDb3
import kotlinx.coroutines.*
import java.lang.Exception

class VModel : ViewModel() {
    lateinit var categorylist: List<Products>
    lateinit var appDb3: AppDb3
    lateinit var vcontext: Context

    private val cartData: MutableLiveData<List<Products>> by lazy {
        MutableLiveData<List<Products>>().also {
            loadCart()
        }
    }

    private val productsData: MutableLiveData<List<Products>> by lazy {
        MutableLiveData<List<Products>>().also {
            loadProducts()
        }
    }

    fun getCartData(data: List<Products>): LiveData<List<Products>> {
        categorylist = data
        return cartData
    }

    fun getProducts(context: Context): LiveData<List<Products>> {
        vcontext = context
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
                        appDb3 = Room
                            .databaseBuilder(vcontext, AppDb3::class.java, "Search")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build()
                        appDb3.searcDao().insertAllProducts(call.body()!!)
                    } else {
                        Log.d("error", "occurred")
                    }
                } catch (e: Exception) {
                    Log.d("load-error", e.toString())
                }
            }
        }
    }
}