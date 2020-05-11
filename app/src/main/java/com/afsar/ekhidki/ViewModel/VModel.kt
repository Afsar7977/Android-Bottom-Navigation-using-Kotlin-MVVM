package com.afsar.ekhidki.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afsar.ekhidki.Models.Category
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class VModel : ViewModel() {
    lateinit var categorylist: List<Category>
    private val cartData: MutableLiveData<List<Category>> by lazy {
        MutableLiveData<List<Category>>().also {
            loadCart()
        }
    }

    fun getCartData(data: List<Category>): LiveData<List<Category>> {
        categorylist = data
        return cartData
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
}