package com.afsar.ekhidki.Models

import com.afsar.ekhidki.Room.AppDb
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Utils {
    private lateinit var user: User

    fun getUser(appDb: AppDb): User {
        val getUser: List<User> = appDb.userDao().getUser()
        val listIterator: ListIterator<User> = getUser.listIterator()
        while (listIterator.hasNext()) {
            user = listIterator.next()
        }
        return user
    }

    companion object{
        var retrofit = Retrofit.Builder()
            .baseUrl("https://www.json-generator.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}