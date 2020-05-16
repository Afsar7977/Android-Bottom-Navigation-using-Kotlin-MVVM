package com.afsar.ekhidki.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.afsar.ekhidki.Models.Products


@Suppress("SENSELESS_COMPARISON")
@Database(entities = [Products::class], version = 2)
abstract class AppDb1 : RoomDatabase() {
    abstract fun productsDao(): ProductsDao

    private lateinit var INSTANCE: AppDb1

    open fun getDatabase(context: Context): AppDb1 {
        if (INSTANCE == null) {
            synchronized(AppDb1::class.java) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder<AppDb1>(
                        context.applicationContext,
                        AppDb1::class.java, "word_database"
                    ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

                }
            }
        }
        return INSTANCE
    }
}