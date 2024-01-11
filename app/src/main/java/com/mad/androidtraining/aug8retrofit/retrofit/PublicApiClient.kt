package com.mad.androidtraining.aug8retrofit.retrofit

import com.mad.androidtraining.aug8retrofit.helper.Constants
import com.mad.androidtraining.aug8retrofit.interfaces.PublicApi
import com.mad.androidtraining.aug8retrofit.interfaces.ReqResApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PublicApiClient private constructor() {
    private val publicApi: PublicApi
    var BASE_URL =Constants.PUBLICAPI


    init {
        val retrofit: Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        publicApi = retrofit.create(PublicApi::class.java)
    }

    fun getPublicApi(): PublicApi {
        return publicApi
    }

    companion object {
        @get:Synchronized
        var instance: PublicApiClient? = null
            get() {
                if (field == null) {
                    field = PublicApiClient()
                }
                return field
            }
            private set
    }
}