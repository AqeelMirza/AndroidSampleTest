package com.example.androidsampletest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.androidsampletest.R
import com.example.androidsampletest.databinding.FragmentThirdBinding
import com.example.androidsampletest.network.responses.UserPhotosResponse
import com.squareup.picasso.Picasso

class ThirdFragment : Fragment() {


    //DataBinding
    private lateinit var binding: FragmentThirdBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_third, container, false
        )
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userPhotoResponse = arguments?.getParcelable<UserPhotosResponse>("selected_album_id")
        setView(userPhotoResponse!!)
    }

    private fun setView(userPhotoResponse: UserPhotosResponse) {
        binding.useralbumId.text = "Album ID: ${userPhotoResponse.albumId.toString()}"
        binding.useralbumPhotoId.text = "Photo ID: ${userPhotoResponse.id.toString()}"
        binding.useralbumText.text = userPhotoResponse.title.toString()
        Picasso.get().load(userPhotoResponse.url).into(binding.useralbumPhoto)
    }
}