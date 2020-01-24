package com.android.kotlindemo.app

open class BaseClass {
    fun string(id: Int): String {
        return MainApplication.applicationContext().getString(id)
    }
}