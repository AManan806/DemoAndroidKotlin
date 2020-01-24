package com.android.kotlindemo.utils

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.util.Log
import com.android.kotlindemo.app.MainApplication
import com.android.kotlindemo.network.ApiServices
import com.android.kotlindemo.tinydb.TinyDB
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Network() {
    private var networkClient: Retrofit? = null
    private var services: ApiServices? = null

    init {
        val gson = GsonBuilder().create()
        val context: Context = MainApplication.applicationContext()
        val httpClient = OkHttpClient.Builder()
        httpClient.retryOnConnectionFailure(true)
        httpClient.connectTimeout(30, TimeUnit.SECONDS)
        httpClient.readTimeout(30, TimeUnit.SECONDS)
        httpClient.writeTimeout(30, TimeUnit.SECONDS)
        httpClient.addInterceptor(getLoggingIntercept())
        httpClient.addInterceptor { chain: Interceptor.Chain ->
            var pInfo: PackageInfo? = null
            try {
                pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
            val version = pInfo!!.versionName
            val tinyDB = TinyDB(MainApplication.applicationContext())
            val token: String? = tinyDB.getString(Constants.token)

            if (!token?.isEmpty()!!) {
                val original = chain.request()
                val request = original.newBuilder()
                    .header("Authorization", "Bearer " + token)
                    .method(original.method(), original.body())
                    .build();
                return@addInterceptor chain.proceed(request)
            } else {
                val request =
                    chain.request().newBuilder().addHeader("androidappversion", version)
                        .addHeader("platform", "android")
                        .build()
                return@addInterceptor chain.proceed(request)
            }
        }
        networkClient = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()
        services = networkClient!!.create(ApiServices::class.java)
    }



    fun getLoggingIntercept(): Interceptor? {
        val logging = HttpLoggingInterceptor(
            HttpLoggingInterceptor.Logger { message: String? ->
                if (ConfigurationFreejna.isProduction) {
                    Log.e("OkHttp", "$message")
                    //                Log.e("OKHTTP::"," "+message);
                } else {
                    Log.e("okHttp", "$message")
                    //                Log.e("OKHTTP::"," 2 ="+message);
                }
            }
        )
        return logging.setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    fun apis(): ApiServices? {
        return getApiServices()
    }


    fun getApiServices(): ApiServices? {
        return services
    }
    open fun getNetworkClient(): Retrofit? {

        return networkClient
    }


}