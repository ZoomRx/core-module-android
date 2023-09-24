package com.zoomrx.coremodule

import android.app.Application
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class AppContainer private constructor(val appContext: Application) {

    companion object {
        private var instance: AppContainer? = null

        @Synchronized
        @JvmStatic
        fun initialize(appContext: Application) {
            if (instance == null) {
                instance = AppContainer(appContext)
            }
        }

        fun getInstance(): AppContainer {
            return instance as AppContainer
        }
    }

    val objectMapper: ObjectMapper by lazy {
        val objectMapperInstance = ObjectMapper()
        objectMapperInstance.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        objectMapperInstance
    }

    val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .callTimeout(60, TimeUnit.SECONDS)
            .build()
    }
}