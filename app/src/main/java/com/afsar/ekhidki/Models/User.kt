package com.afsar.ekhidki.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["email", "password"])
data class User(
    @SerializedName("fname")
    var fname: String?,
    @SerializedName("lname")
    var lname: String?,
    @SerializedName("age")
    var age: String?,
    @SerializedName("gender")
    var gender: String?,
    @ColumnInfo(name = "email")
    var email: String,
    @ColumnInfo(name = "password")
    var password: String
)