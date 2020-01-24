package com.android.kotlindemo.network

/**
 * Created by devandro on 11/20/17.
 */
object ResponseCode {
    var SUCCESS = 200
    var INTERNAL_SERVER_ERROR = 500
    var FORBIDDEN = 403
    var UN_AUTHORIZED = 401
    var ERROR = 422
    var APP_NOT_UPDATED = 304
    fun isBetweenSuccessRange(reqCode: Int): Boolean {
        return try {
            reqCode > 199 && reqCode < 300
        } catch (e: Exception) {
            true
        }
    }
}