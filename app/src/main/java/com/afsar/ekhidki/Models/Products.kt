package com.afsar.ekhidki.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Products(
    @ColumnInfo(name = "price")
    var price: Int,
    @SerializedName("image")
    @ColumnInfo(name = "image")
    var image: String,
    @SerializedName("desc")
    @ColumnInfo(name = "desc")
    var desc: String,
    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    @SerializedName("name")
    @ColumnInfo(name = "name")
    var name: String
)