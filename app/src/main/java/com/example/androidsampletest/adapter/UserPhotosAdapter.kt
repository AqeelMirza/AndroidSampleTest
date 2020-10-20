package com.example.androidsampletest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidsampletest.R
import com.example.androidsampletest.databinding.ItemUserphototsBinding
import com.example.androidsampletest.network.responses.UserPhotosResponse
import com.squareup.picasso.Picasso

typealias albumItemClickListener = (userPhotoResponse: UserPhotosResponse) -> Unit

class UserPhotosAdapter(private val onClickListener: albumItemClickListener) :
    RecyclerView.Adapter<UserViewHolder>() {

    private val userPhotosList = ArrayList<UserPhotosResponse>()
    var position: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemUserphototsBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_userphotots, parent, false)
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userPhotosList.size
    }

    //Setting the response list
    fun setList(userPhotosResponse: List<UserPhotosResponse>) {
        userPhotosList.clear()
        userPhotosResponse.let { userPhotosList.addAll(it) }
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        this.position = position
        holder.bind(userPhotosList[position])
        holder.itemView.setOnClickListener { view ->
            onClickListener(userPhotosList.get(position))
        }
    }
}

class UserViewHolder(val binding: ItemUserphototsBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(userPhotosResponse: UserPhotosResponse) {
        binding.userphotoText.text = "${userPhotosResponse.title}"
        Picasso.get().load(userPhotosResponse.thumbnailUrl).into(binding.userphotoPhoto)
    }
}
