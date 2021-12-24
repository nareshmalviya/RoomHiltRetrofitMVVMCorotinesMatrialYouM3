package com.codelab.roomhiltretrofitmvvmcorotinesmatrialyoum3.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.codelab.roomhiltretrofitmvvmcorotinesmatrialyoum3.R
import com.codelab.roomhiltretrofitmvvmcorotinesmatrialyoum3.data.local.User
import com.codelab.roomhiltretrofitmvvmcorotinesmatrialyoum3.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var userListAdapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.mainviewmodel = mainViewModel
        binding.lifecycleOwner =  this
       // getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar()?.setDisplayShowHomeEnabled(true);
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        mainViewModel.statusMessage.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this,it.toString(),Toast.LENGTH_LONG).show()
            }

        })

        initRrcyclerview()

    }


    fun initRrcyclerview(){

        userListAdapter = UserListAdapter({ user: User -> userListClick(user)})
        binding.userlistRecv.adapter = userListAdapter
        displayUserList()
    }


    fun  displayUserList(){
        mainViewModel.getalluser().observe(this, Observer {
            userListAdapter.setList(it)
            userListAdapter.notifyDataSetChanged()
        })

    }


    fun userListClick(user: User){
        mainViewModel.initialisemodel(user)
    }


}