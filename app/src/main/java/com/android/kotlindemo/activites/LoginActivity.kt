package com.android.kotlindemo.activites

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.android.kotlindemo.R
import com.android.kotlindemo.checkLogin.LoginAuth
import com.android.kotlindemo.network.NetworkCall
import com.android.kotlindemo.network.OnNetworkResponse
import com.android.kotlindemo.network.serializer.BaseResponse
import com.android.kotlindemo.network.serializer.ModelAPIResponse
import com.android.kotlindemo.network.serializer.ModelLogin
import com.android.kotlindemo.utils.Network
import com.android.kotlindemo.utils.Notify
import com.android.kotlindemo.utils.RequestCodes
import com.google.gson.internal.LinkedTreeMap
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity(), View.OnClickListener
    , OnNetworkResponse {

    private var phoneNumber: EditText? = null
    private val networkClass = Network()
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialization()
        setOnclickListeners()
        populateValues()

    }

    private fun initialization() {
        phoneNumber = findViewById(R.id.et_phoneNumber)
    }

    private fun populateValues() {

    }

    private fun setOnclickListeners() {
        btn_continue.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_continue -> {

                val authSuccessful: Boolean = LoginAuth.authenticate(
                    phoneNumber?.text.toString(), this
                )
                if (authSuccessful) {
                    NetworkCall.make()
                        ?.setCallback(this)
                        ?.setTag(RequestCodes.API.LOGIN)
                        ?.enque(
                            networkClass.apis()?.Login(
                                ModelLogin(
                                    ccp.selectedCountryCodeWithPlus.replace(" ".toRegex(), ""),
                                    phoneNumber?.text.toString()
                                )
                            )
                        )
                        ?.execute()
                }
            }
        }
    }


    override fun onSuccess(call: Call<Any?>?, response: Response<Any?>, tag: Any?) {
        when (tag) {
            RequestCodes.API.LOGIN -> {

                val apiResponse = ModelAPIResponse(response.body() as LinkedTreeMap<String, String>)
                Notify.alerterGreen(
                    this,
                    "${apiResponse.message}"
                )

                /*   val apiResponse = response.body() as LinkedTreeMap<*, *>

                   val toast = Toast.makeText(
                       MainApplication.applicationContext(),
                       "Api Success=${ apiResponse["message"]}",
                       Toast.LENGTH_SHORT
                   )
                   toast.show()*/
            }
        }
    }

    override fun onFailure(call: Call<Any?>?, response: BaseResponse?, tag: Any?) {
        when (tag) {
            RequestCodes.API.LOGIN -> {
                Notify.alerterRed(
                    this,
                    "${response?.message}"
                )
            }
        }
    }


}
