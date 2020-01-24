package com.android.kotlindemo.utils

import com.android.kotlindemo.R
import com.android.kotlindemo.app.MainApplication
import java.util.*

object ConfigurationFreejna {
    const val DB_VERSION: Long = 2
    private val environment = Environments.Development
    @JvmStatic
    val baseUrl: ArrayList<String>
        get() {
            val links = ArrayList<String>()
            return if (environment == Environments.Development) {
                links.add(MainApplication.applicationContext().getString(R.string.development_freejna))
                links.add(MainApplication.applicationContext().getString(R.string.development_freejna_image))
                links.add(MainApplication.applicationContext().getString(R.string.development_freejna_deeplink))
                links
            } else if (environment == Environments.Production) {
                links.add(MainApplication.applicationContext().getString(R.string.production_freejna))
                links.add(MainApplication.applicationContext().getString(R.string.production_freejna_image))
                links.add(MainApplication.applicationContext().getString(R.string.production_freejna_deepLink))
                links
            } else if (environment == Environments.Local) {
                links.add(MainApplication.applicationContext().getString(R.string.local_freejna))
                links.add(MainApplication.applicationContext().getString(R.string.local_freejna_image))
                links.add(MainApplication.applicationContext().getString(R.string.local_freejna_deeplink))
                links
            } else {
                links.add(MainApplication.applicationContext().getString(R.string.freejna_stagging))
                links.add(MainApplication.applicationContext().getString(R.string.freejna_stagging_image))
                links.add(MainApplication.applicationContext().getString(R.string.freejna_stagging_deeplink))
                links
            }
        }

    val isProduction: Boolean
        get() {
            val isProduction: Boolean
            isProduction = try {
                environment == Environments.Production
            } catch (e: Exception) {
                false
            }
            return isProduction
        }

    val isDevelopment: Boolean
        get() {
            val isDevelopment: Boolean
            isDevelopment = try {
                environment == Environments.Development
            } catch (e: Exception) {
                false
            }
            return isDevelopment
        }

    val isLocal: Boolean
        get() {
            val isLocal: Boolean
            isLocal = try {
                environment == Environments.Local
            } catch (e: Exception) {
                false
            }
            return isLocal
        }

    val envName: String
        get() = environment.toString()

    enum class Environments {
        Development, Production, Local, Staging
    }
}