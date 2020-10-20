package com.example.androidsampletest.network


import com.example.androidsampletest.network.responses.UserInfo
import com.example.androidsampletest.network.responses.UserPhotosResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface MyApi {

    @GET("users")
    suspend fun userInfo(
    ): Response<List<UserInfo>>

    @GET("photos")
    suspend fun userPhotos(
    ): Response<List<UserPhotosResponse>>


    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): MyApi {
            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl(" https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }

}