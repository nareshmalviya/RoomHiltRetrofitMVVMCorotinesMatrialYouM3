package com.codelab.roomhiltretrofitmvvmcorotinesmatrialyoum3.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.codelab.roomhiltretrofitmvvmcorotinesmatrialyoum3.data.local.UserDAO
import com.codelab.roomhiltretrofitmvvmcorotinesmatrialyoum3.data.local.UserDataBase
import com.codelab.roomhiltretrofitmvvmcorotinesmatrialyoum3.data.remote.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UserModule {
    var baseUrl= "https://reqres.in/"

    @Provides
    @Singleton
    fun provideRommDB(application: Application):UserDataBase{
        return Room.databaseBuilder(application,UserDataBase::class.java,"userdb").build()
    }

    @Provides
    @Singleton
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideRetrofitService(retrofit: Retrofit):RetrofitService{
        return retrofit.create(RetrofitService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserDao(userDataBase: UserDataBase):UserDAO{
        return userDataBase.userDao
    }


}