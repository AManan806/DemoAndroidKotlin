package com.android.kotlindemo.utils

import android.app.Activity
import android.content.Context
import android.widget.Toast
import com.android.kotlindemo.R
import com.android.kotlindemo.app.MainApplication
import com.tapadoo.alerter.Alerter

object Notify {
    const val SUCCESS_COLOR = "#22A109"
    const val FAILURE_COLOR = "#EC0909"
    //    private static Snackbar snackbar;

    //    private static Snackbar snackbar;
    fun Toast(message: String?) {
        Toast.makeText(
            MainApplication.applicationContext(),
            message,
            Toast.LENGTH_SHORT
        ).show()
    }
    fun alerterRed(
        activity: Context?,
        message: String?
    ) {
        if (activity != null) Alerter.create(activity as Activity?)
            .setText(message!!)
            .setIcon(R.drawable.warning_sign)
            .setBackgroundColorRes(R.color.light_orange)
            .show()
    }

    fun alerterGreen(activity: Activity?, message: String?) {
        if (activity != null) Alerter.create(activity)
            .setText(message!!)
            .setTextAppearance(R.color.white)
            .setBackgroundColorRes(R.color.light_green)
            .show()
    }
}