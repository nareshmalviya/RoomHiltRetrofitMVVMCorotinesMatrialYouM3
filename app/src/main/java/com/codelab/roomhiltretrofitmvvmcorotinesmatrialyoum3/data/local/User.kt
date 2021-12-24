package com.codelab.roomhiltretrofitmvvmcorotinesmatrialyoum3.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usertable")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userid")
    var userid:Int,
    @ColumnInfo(name = "username")
    var username:String,
    @ColumnInfo(name = "useremail")
    var useremail:String
)
