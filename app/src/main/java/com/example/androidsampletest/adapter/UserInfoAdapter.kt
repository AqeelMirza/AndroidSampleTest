package com.example.androidsampletest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidsampletest.R
import com.example.androidsampletest.databinding.ItemUserinfoBinding
import com.example.androidsampletest.network.responses.UserInfo


typealias itemClickListener = (Pos: Int) -> Unit

class UserInfoAdapter(private val onClickListener: itemClickListener) :
    RecyclerView.Adapter<MyViewHolder>() {

    private val userInfoList = ArrayList<UserInfo>()
    var position: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemUserinfoBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_userinfo, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        this.position = position
        holder.bind(userInfoList[position])
        holder.itemView.setOnClickListener { view ->
            onClickListener(position + 1)
        }
    }

    override fun getItemCount(): Int {
        return userInfoList.size
    }

    //Setting the response list
    fun setList(userInfoResponse: List<UserInfo>) {
        userInfoList.clear()
        userInfoResponse.let { userInfoList.addAll(it) }
    }

}

class MyViewHolder(val binding: ItemUserinfoBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(userInfo: UserInfo) {
        binding.userinfoId.text = "ID: ${userInfo.id}"
        binding.userinfoName.text = "Name: ${userInfo.name}"
        binding.userinfoEmail.text = "Email: ${userInfo.email}"
        binding.userinfoPhone.text = "Phone: ${userInfo.phone}"

    }
}
