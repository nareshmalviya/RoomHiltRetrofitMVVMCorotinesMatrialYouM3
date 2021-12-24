package com.codelab.roomhiltretrofitmvvmcorotinesmatrialyoum3.ui

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.codelab.roomhiltretrofitmvvmcorotinesmatrialyoum3.data.local.DataBaseRepository
import com.codelab.roomhiltretrofitmvvmcorotinesmatrialyoum3.data.local.User
import com.codelab.roomhiltretrofitmvvmcorotinesmatrialyoum3.databinding.ActivityMainBinding
import com.codelab.roomhiltretrofitmvvmcorotinesmatrialyoum3.util.GeneralEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(val dataBaseRepository: DataBaseRepository) :ViewModel(){
    var inputName = MutableLiveData<String>()
    var inputEmail = MutableLiveData<String>()
    var saveOrUpdateButtonText = MutableLiveData<String>()
    var clearAllOrDeleteButtonText = MutableLiveData<String>()
    var statusMessage = MutableLiveData<GeneralEvent<String>>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var usermodel : User
    private var isUpdate =false
    //val message: LiveData<GeneralEvent<String>> get() = statusMessage
    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"


    }


    fun getalluser() = liveData {

        dataBaseRepository.userList.collect { emit(it) }

    }

    fun saveOrUpdate(){

        if (inputName.value.isNullOrEmpty()){
            statusMessage.value = GeneralEvent("Please enter subscriber's name")
        }else  if (inputEmail.value.isNullOrEmpty()){
            statusMessage.value = GeneralEvent("Please enter subscriber's email")
        }else  if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail.value!!).matches()){
            statusMessage.value = GeneralEvent("Please enter correct email formate")
        }else{
            if (isUpdate){
                usermodel.username=inputName.value!!
                usermodel.useremail=inputEmail.value!!
                updateuser(usermodel)
            }else{
                val name  =inputName.value!!
                val email=inputEmail.value!!
                insertuser(User(0,name,email))
            }
            cleartext()
        }
    }

    private fun updateuser(user: User) =viewModelScope.launch {
        val noOfRows =  dataBaseRepository.updatetUser(user)
        if (noOfRows>-1){
            statusMessage.value = GeneralEvent("User Updated")
            updatebutton(false)
        }else{
            statusMessage.value = GeneralEvent("Error Occurred")
        }
    }

    private fun insertuser(user: User) =viewModelScope.launch {
        val newRowId = dataBaseRepository.insertUser(user)
        if (newRowId>-1){
            statusMessage.value = GeneralEvent("User Inserted")

        }else{
            statusMessage.value = GeneralEvent("Error Occurred")
        }
    }

    fun clearOrDelete(){
        if (isUpdate){
            deleteuser(usermodel)
        }else{
            deletall()
        }
        cleartext()
    }


    private fun deleteuser(user: User) =viewModelScope.launch {
        val noOfRows =  dataBaseRepository.deleteUser(user)
        if (noOfRows>-1){
            statusMessage.value = GeneralEvent("User Deleted")
            updatebutton(false)
        }else{
            statusMessage.value = GeneralEvent("Error Occurred")
        }
    }

    private fun deletall() =viewModelScope.launch {
        val newRowId = dataBaseRepository.deleteAllUser()
        if (newRowId>-1){
            statusMessage.value = GeneralEvent("All User Deleted")
        }else{
            statusMessage.value = GeneralEvent("Error Occurred")
        }
    }

    fun initialisemodel(user: User) {
        inputName.value = user.username
        inputEmail.value = user.useremail
        usermodel = user
        updatebutton(true)
    }

    fun cleartext(){
        inputName.value = ""
        inputEmail.value = ""
    }

    fun updatebutton(update:Boolean){
        if (update){
            isUpdate = true
            saveOrUpdateButtonText.value = "Update"
            clearAllOrDeleteButtonText.value = "Delete"
        }else{
            isUpdate = false
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"
        }

    }

}