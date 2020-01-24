package com.android.kotlindemo.network.serializer

class ModelLogin {
    var countryCode: String
    var mobileNumber: String
    var otp: String? = null

    constructor(countryCode: String, mobileNumber: String) {
        this.countryCode = countryCode
        this.mobileNumber = mobileNumber
    }

    constructor(countryCode: String, mobileNumber: String, otp: String?) {
        this.countryCode = countryCode
        this.mobileNumber = mobileNumber
        this.otp = otp
    }
}