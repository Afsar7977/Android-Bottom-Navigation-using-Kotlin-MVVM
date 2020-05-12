package com.afsar.ekhidki.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.afsar.ekhidki.Models.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getUser(): LiveData<List<User>>

    @Query(
        "SELECT * FROM user WHERE email LIKE :email AND " +
                "password LIKE :password LIMIT 1"
    )
    fun findByName(email: String, password: String): User

    @Insert
    fun insertUser(user: User)

    @Delete
    fun deleteUser(user: User)
}