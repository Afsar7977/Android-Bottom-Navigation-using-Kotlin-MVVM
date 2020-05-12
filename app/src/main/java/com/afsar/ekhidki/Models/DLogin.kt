package com.afsar.ekhidki.Models

import androidx.annotation.NonNull
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["name", "password"])
data class DLogin(
    @NonNull
    @SerializedName("email")
    var email: String,
    @NonNull
    @SerializedName("password")
    var password: String
)