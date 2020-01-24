package com.android.kotlindemo.network

import com.android.kotlindemo.R
import com.android.kotlindemo.app.BaseClass
import com.android.kotlindemo.network.serializer.BaseResponse
import com.android.kotlindemo.utils.ConfigurationFreejna
import com.android.kotlindemo.utils.Utils
import com.google.gson.stream.MalformedJsonException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import javax.net.ssl.SSLException
import javax.net.ssl.SSLHandshakeException

object NetworkCall : BaseClass() {

    //    var viewDialog: ViewDialog? = null
    private var taggedObject: Any? = null
    private var callback: OnNetworkResponse? = null
    private var request: Call<Any>? = null

    @Synchronized
    fun make(): NetworkCall? {
        return NetworkCall
    }

    fun setCallback(callback: OnNetworkResponse?): NetworkCall? {
        this.callback = callback
        return this
    }

    /*
        fun autoLoadigCancel(loading: Loading): NetworkCall? {
            loading = loading
            return this
        }*/
    fun setTag(tag: Any?): NetworkCall? {
        taggedObject = tag
        return this
    }

    fun enque(call: Call<Any>?): NetworkCall? {
        request = call
        return this
    }

    fun execute(): NetworkCall? {
        request!!.enqueue(object : Callback<Any?> {
            override fun onResponse(call: Call<Any?>?, response: Response<Any?>) {

                if (BaseResponse.isSuccess(response)) {
                    callback!!.onSuccess(call, response, taggedObject)

                } else if (response.body() == null || !BaseResponse.isSuccess(response)) {
                    callback!!.onFailure(
                        call,
                        BaseResponse(
                            response.raw().message().toString(),
                            response.raw().code().toString()
                        ),
                        taggedObject
                    )
//                    callback!!.onFailure(call, response.body() as? BaseResponse, taggedObject)
                }
            }

            override fun onFailure(call: Call<Any?>, t: Throwable) {
                val response: BaseResponse =
                    if (Utils.isCause(SocketTimeoutException::class.java, t)) {
                        BaseResponse(string(R.string.timeout))
                    } else if (Utils.isCause(ConnectException::class.java, t)) {
                        BaseResponse(string(R.string.connectException))
                    } else if (Utils.isCause(
                            MalformedJsonException::class.java,
                            t
                        )
                    ) {
                        BaseResponse(string(R.string.invalid_data))
                    } else if (t is SSLHandshakeException || t is SSLException) {
                        BaseResponse(string(R.string.ssl))
                    } else if (t is IOException) {
                        BaseResponse(string(R.string.no_internet))
                    } else {
                        BaseResponse(if (ConfigurationFreejna.isDevelopment) t.message else string(R.string.text_somethingwentwrong))
                        //  response = new BaseResponse(t.getMessage());
                    }
                callback!!.onFailure(call, response, taggedObject)
            }

        })
        return this
    }
    /*fun parseErrorResponse(response: Response<*>): BaseResponse? {
        var errorResponse: BaseResponse? = null
        return try {
            if (ResponseCode.isBetweenSuccessRange(response.code())) {
                response.body() as BaseResponse?
            } else {
                val errorConverter: Converter<ResponseBody?, BaseResponse> =
                    Network().getNetworkClient()!!.responseBodyConverter(
                        BaseResponse::class.java, arrayOfNulls<Annotation>(0)
                    )
                errorResponse = errorConverter.convert(response.errorBody())
                errorResponse?.message= response.code().toString()
                if (errorResponse?.message == null
                    || errorResponse?.message.equals("")
                ) { //                    Notify.Toast(errorResponse.getMessage()+ "code: " +errorResponse.getCode());
                    errorResponse?.message="Unable to communicate with server, please try again."
                }
                errorResponse
            }
        } catch (e: Exception) {
            errorResponse?.code = response.code().toString()
            var errorString: String?
            try {
                errorString = response.errorBody()!!.string()
            } catch (ex: Exception) {
                errorString = "Exception in parsing error response"
            }
            if (errorString == null || errorString.trim { it <= ' ' }.equals(
                    "",
                    ignoreCase = true
                )
            ) {
                errorString =
                    "Unable to communicate with " + " server, try again."
            }
            errorResponse?.message =errorString
            errorResponse
        }
    }*/


}