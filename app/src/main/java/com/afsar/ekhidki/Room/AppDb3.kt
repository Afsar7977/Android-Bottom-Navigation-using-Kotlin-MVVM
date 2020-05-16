package com.afsar.ekhidki.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.afsar.ekhidki.Models.Products

@Database(entities = [Products::class], version = 2)
abstract class AppDb3 : RoomDatabase() {
    abstract fun searcDao(): SearchDao
}