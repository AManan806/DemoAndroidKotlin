package com.android.kotlindemo.checkLogin

import com.android.kotlindemo.MainActivity
import com.android.kotlindemo.app.MainApplication
import com.android.kotlindemo.R
import com.android.kotlindemo.utils.InternetCheck
import com.android.kotlindemo.utils.Notify


object LoginAuth {
    fun authenticate(phoneNumber: String?, activity: MainActivity): Boolean {
        var check = false
        if (phoneNumber?.isEmpty()!! || phoneNumber.length < 6) {
            if (phoneNumber.isEmpty()) {
                Notify.alerterRed(
                    activity,
                    MainApplication.applicationContext().resources.getString(R.string.enter_phone_number_err_msg)
                )
                check = false
            } else if (phoneNumber.length < 6) {
                Notify.alerterRed(
                    activity,
                    MainApplication.applicationContext().resources.getString(R.string.phone_length_err_msg)
                )
                check = false
            }

        } else {
            if (InternetCheck.getInstance()?.isNetworkAvailable(MainApplication.applicationContext())!!) {
                check = true
            } else {
                InternetCheck.getInstance()?.alertDialog(activity)
                check=false
            }
        }
        return check
    }
}