package com.example.androidsampletest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidsampletest.R
import com.example.androidsampletest.adapter.UserPhotosAdapter
import com.example.androidsampletest.databinding.FragmentUserBinding
import com.example.androidsampletest.network.responses.UserPhotosResponse
import com.example.androidsampletest.utils.ApiException
import com.example.androidsampletest.utils.NoInternetException
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class SecondFragment : Fragment(), KodeinAware {
    //Dependency Injection
    override val kodein: Kodein by kodein()
    private val factory: UserViewModelFactory by instance()

    //DataBinding
    private lateinit var binding: FragmentUserBinding

    //Viewmodel
    private lateinit var userViewModel: UserViewModel

    //RecyclerViewAdapter
    private lateinit var adapter: UserPhotosAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_user, container, false
        )
        val view = binding.root
        userViewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getInt("selected_id")
        Toast.makeText(
            activity, "${id}", Toast.LENGTH_SHORT
        )
        setHeader(id!!)
        initRecyclerView()
        getUserPhotos(id!!)
    }

    private fun setHeader(id: Int) {
        binding.headerText.text = "Album ID: ${id}"
    }

    private fun initRecyclerView() {
        binding.recyclerviewFirst.layoutManager =
            LinearLayoutManager(activity)
        adapter = UserPhotosAdapter(onClickListener = this::navigateToNextPage)
        binding.recyclerviewFirst.adapter = adapter
    }

    private fun navigateToNextPage(userPhotosResponse: UserPhotosResponse) {
        val bundle = bundleOf("selected_album_id" to userPhotosResponse)
        findNavController().navigate(R.id.action_SecondFragment_to_ThirdFragment, bundle)
    }

    private fun getUserPhotos(id: Int) {
        lifecycleScope.launch {
            try {
                val userPhotosResponse = userViewModel.getUserPhotos()
                val filterdList = ArrayList<UserPhotosResponse>()
                for (x in 0 until userPhotosResponse.size) {
                    if (id == userPhotosResponse.get(x).albumId) {
                        filterdList.add(userPhotosResponse.get(x))
                    }
                }
                displayReceiverList(filterdList)
            } catch (e: ApiException) {
                e.printStackTrace()
            } catch (e: NoInternetException) {
                e.printStackTrace()
            }
        }
    }

    private fun displayReceiverList(receiverResponse: List<UserPhotosResponse>) {
        adapter.setList(receiverResponse)
        adapter.notifyDataSetChanged()
    }
}