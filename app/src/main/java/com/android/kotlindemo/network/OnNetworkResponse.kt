package com.android.kotlindemo.network

import com.android.kotlindemo.network.serializer.BaseResponse
import retrofit2.Call
import retrofit2.Response

interface OnNetworkResponse {
    fun onSuccess(
        call: Call<Any?>?,
        response: Response<Any?>,
        tag: Any?
    )

    fun onFailure(
        call: Call<Any?>?,
        response: BaseResponse?,
        tag: Any?
    ) //default void statusCode(Call call, Response response, Object tag , int statusCode){}
}