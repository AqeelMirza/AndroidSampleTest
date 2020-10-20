package com.example.androidsampletest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidsampletest.repo.UserRepo


@Suppress("UNCHECKED_CAST")
class UserViewModelFactory(
    private val repo: UserRepo
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserViewModel(repo) as T
    }
}