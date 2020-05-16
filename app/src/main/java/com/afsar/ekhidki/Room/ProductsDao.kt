package com.afsar.ekhidki.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.afsar.ekhidki.Models.Products

@Dao
interface ProductsDao {
    @Query("SELECT * FROM products")
    fun getProducts(): List<Products>

    @Insert
    fun insertProducts(list: List<Products>)

    @Insert
    fun insertProduct(products: Products)
}