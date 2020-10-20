package com.example.androidsampletest.ui

import androidx.lifecycle.ViewModel
import com.example.androidsampletest.repo.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserViewModel(private val repo: UserRepo) : ViewModel() {

    suspend fun getUserInfo() = withContext(Dispatchers.IO) { repo.userInfo() }
    suspend fun getUserPhotos() = withContext(Dispatchers.IO) { repo.userPhotos() }

}
