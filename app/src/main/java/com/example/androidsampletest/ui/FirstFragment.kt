package com.example.androidsampletest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidsampletest.R
import com.example.androidsampletest.adapter.UserInfoAdapter
import com.example.androidsampletest.databinding.FragmentUserBinding
import com.example.androidsampletest.network.responses.UserInfo
import com.example.androidsampletest.utils.ApiException
import com.example.androidsampletest.utils.NoInternetException
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class FirstFragment : Fragment(), KodeinAware {

    //Dependency Injection
    override val kodein: Kodein by kodein()
    private val factory: UserViewModelFactory by instance()

    //DataBinding
    private lateinit var binding: FragmentUserBinding

    //Viewmodel
    private lateinit var userViewModel: UserViewModel

    //RecyclerViewAdapter
    private lateinit var adapter: UserInfoAdapter

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

    private fun setHeader() {
        binding.headerText.text = resources.getString(R.string.userinfo_first_fragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHeader()
        initRecyclerView()
        getUserInfo()
    }

    private fun initRecyclerView() {
        binding.recyclerviewFirst.layoutManager =
            LinearLayoutManager(activity)
        adapter = UserInfoAdapter(onClickListener = this::navigateToNextPage)
        binding.recyclerviewFirst.adapter = adapter
    }

    private fun navigateToNextPage(pos: Int) {
        val bundle = bundleOf("selected_id" to pos)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
    }

    private fun getUserInfo() {
        lifecycleScope.launch {
            try {
                val userInfoResponse = userViewModel.getUserInfo()
                displayReceiverList(userInfoResponse)
            } catch (e: ApiException) {
                // toast(e.message.toString())
                e.printStackTrace()
            } catch (e: NoInternetException) {
                e.printStackTrace()
            }
        }
    }

    private fun displayReceiverList(receiverResponse: List<UserInfo>) {
        adapter.setList(receiverResponse)
        adapter.notifyDataSetChanged()
    }
}