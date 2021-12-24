package com.codelab.roomhiltretrofitmvvmcorotinesmatrialyoum3.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.codelab.roomhiltretrofitmvvmcorotinesmatrialyoum3.R
import com.codelab.roomhiltretrofitmvvmcorotinesmatrialyoum3.data.local.User
import com.codelab.roomhiltretrofitmvvmcorotinesmatrialyoum3.databinding.ListItemBinding

class UserListAdapter(private val userListClick : (User)->Unit )  : RecyclerView.Adapter<UserListAdapter.UserHolder>(){

    private val subscribersList = ArrayList<User>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val binding:ListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout
            .list_item,parent,false)
       return UserHolder(binding)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(subscribersList[position],userListClick)
    }

    override fun getItemCount(): Int {
       return subscribersList.size
    }
    fun setList(subscribers: List<User>) {
        subscribersList.clear()
        subscribersList.addAll(subscribers)

    }

    inner class UserHolder(val binding: ListItemBinding) :RecyclerView.ViewHolder(binding.root){

        fun bind(user: User,userListClick: (User) -> Unit){

            binding.nameTextView.text = user.username
            binding.emailTextView.text = user.useremail
            binding.cardView.setOnClickListener { userListClick(user) }

        }

    }
}