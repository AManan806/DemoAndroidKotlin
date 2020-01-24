package com.android.kotlindemo.network

import com.android.kotlindemo.network.serializer.ModelLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiServices {
    @POST("Auth/Login")
    fun Login(@Body body: ModelLogin?): Call<Any>?

}