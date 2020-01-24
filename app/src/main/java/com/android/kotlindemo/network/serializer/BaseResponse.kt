package com.android.kotlindemo.network.serializer

import android.util.Log
import com.android.kotlindemo.network.ResponseCode
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import java.io.Serializable

class BaseResponse : Serializable {
    @SerializedName("statusCode")
    @Expose
    var code: String? = null
    @SerializedName("message")
    @Expose
    var message: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null

    constructor(message: String?) {
        this.message = message
    }
    constructor(message: String?,code: String?) {
        this.message = message
        this.code=code
    }



    companion object {
        fun isSuccess(response: Response<*>): Boolean {
            var check = false
            try { //            return ResponseCode.isBetweenSuccessRange(Integer.parseInt(((BaseResponse) response.body()).getCode()));
                if (response.code() == ResponseCode.SUCCESS) {
                    check = true
                }
            } catch (e: Exception) {
                check = false
                Log.e("Base Response check=$check", "Exception$e")
            }
            return check
        }

        fun isUnAuthorized(response: Response<*>): Boolean {
            return try {
                (response.code() == ResponseCode.FORBIDDEN
                        || response.code() == ResponseCode.UN_AUTHORIZED)
            } catch (e: Exception) {
                false
            }
        }

        fun codeSetter(code: String): String? {
            return code
        }
        fun messageSetter(code: String): String? {
            return code
        }


    }

}