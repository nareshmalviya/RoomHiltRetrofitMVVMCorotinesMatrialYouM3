package com.codelab.roomhiltretrofitmvvmcorotinesmatrialyoum3.data.local



import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {

    @Query("SELECT * FROM usertable")
    fun getAllUserfromDB():Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User):Long

    @Update
    suspend fun updatetUser(user: User):Int

    @Delete
    suspend fun deleteUser(user: User):Int


    @Query("DELETE FROM usertable")
    suspend fun deleteAllUser():Int

}