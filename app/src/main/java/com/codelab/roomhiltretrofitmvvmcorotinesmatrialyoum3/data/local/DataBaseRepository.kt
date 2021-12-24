package com.codelab.roomhiltretrofitmvvmcorotinesmatrialyoum3.data.local

import androidx.room.*
import javax.inject.Inject

class DataBaseRepository @Inject constructor( private val  userDAO: UserDAO) {


    suspend fun insertUser(user: User): Long {
        return userDAO.insertUser(user)
    }


    suspend fun updatetUser(user: User):Int{
        return userDAO.updatetUser(user)
    }


    suspend fun deleteUser(user: User):Int{
        return userDAO.deleteUser(user)
    }


    suspend fun deleteAllUser():Int{
        return userDAO.deleteAllUser()
    }

    var userList = userDAO.getAllUserfromDB()

}