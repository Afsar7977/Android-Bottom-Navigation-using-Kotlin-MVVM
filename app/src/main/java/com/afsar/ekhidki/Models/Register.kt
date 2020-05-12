package com.afsar.ekhidki.Models

import androidx.annotation.NonNull
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["email", "password"])
data class Register(
    @SerializedName("fname")
    var fname: String,
    @SerializedName("lname")
    var lname: String,
    @SerializedName("age")
    var age: String,
    @SerializedName("gender")
    var gender: String,
    @NonNull
    @SerializedName("email")
    var email: String,
    @SerializedName("phone")
    var phone: String,
    @NonNull
    @SerializedName("password")
    var password: String
)