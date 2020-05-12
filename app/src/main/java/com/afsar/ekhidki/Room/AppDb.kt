package com.afsar.ekhidki.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.afsar.ekhidki.Models.User

@Database(entities = [User::class], version = 2)
abstract class AppDb : RoomDatabase() {
    abstract fun userDao(): UserDao
}