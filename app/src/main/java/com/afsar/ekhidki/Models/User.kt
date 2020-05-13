package com.afsar.ekhidki.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["email", "password"])
data class User(
    @ColumnInfo(name = "fname")
    var fname: String?,
    @ColumnInfo(name = "lname")
    var lname: String?,
    @ColumnInfo(name = "age")
    var age: String?,
    @ColumnInfo(name = "gender")
    var gender: String?,
    @ColumnInfo(name = "email")
    var email: String,
    @ColumnInfo(name = "password")
    var password: String
)