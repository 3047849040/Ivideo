package com.example.ivideo.model

import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import com.example.ivideo.model.RetrofitManager

class RetrofitManager private constructor() {
    var retrofit: Retrofit? = null
        get() {
            if (field == null) {
                val build = OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()
                field = Retrofit.Builder()
                    .client(build)
                    .baseUrl("http://124.70.98.255:8083/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return field
        }
        private set

    companion object {
        private var retrofitManager: RetrofitManager? = null
        val instance: RetrofitManager?
            get() {
                if (retrofitManager == null) {
                    synchronized(RetrofitManager::class.java) {
                        if (retrofitManager == null) {
                            retrofitManager = RetrofitManager()
                        }
                    }
                }
                return retrofitManager
            }
    }
}