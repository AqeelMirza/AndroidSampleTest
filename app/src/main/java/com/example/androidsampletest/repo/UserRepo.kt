package com.example.androidsampletest.repo

import com.example.androidsampletest.network.MyApi
import com.example.androidsampletest.network.SafeApiRequest
import com.example.androidsampletest.network.responses.UserInfo
import com.example.androidsampletest.network.responses.UserPhotosResponse

class UserRepo(
    private val api: MyApi
) : SafeApiRequest() {

    suspend fun userInfo(): List<UserInfo> {
        return apiRequest { api.userInfo() }
    }

    suspend fun userPhotos(): List<UserPhotosResponse> {
        return apiRequest { api.userPhotos() }
    }

}