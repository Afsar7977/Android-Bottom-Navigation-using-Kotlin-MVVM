package com.afsar.ekhidki.Models

import com.afsar.ekhidki.Room.AppDb

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
}