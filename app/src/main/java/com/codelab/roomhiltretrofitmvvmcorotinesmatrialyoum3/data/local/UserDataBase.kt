package com.codelab.roomhiltretrofitmvvmcorotinesmatrialyoum3.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class],version = 1)
 abstract class UserDataBase :RoomDatabase() {

    abstract val userDao:UserDAO
}