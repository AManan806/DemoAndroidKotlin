package com.android.kotlindemo.network.serializer

import com.google.gson.internal.LinkedTreeMap

class ModelAPIResponse(data: LinkedTreeMap<String, String>) {
    var statusCode: String? = null
    var message: String? = null

    init {
        this.message = data["message"]
        this.statusCode = data["statusCode"]
    }

}