package com.mad.androidtraining.aug8retrofit.retrofit

import com.mad.androidtraining.aug8retrofit.helper.Constants
import com.mad.androidtraining.aug8retrofit.interfaces.ReqResApi
import com.mad.androidtraining.july31retrofit.interfaces.UsersApi
import com.mad.androidtraining.july31retrofit.retrofit.RetrofitClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ReqResApiClient  private constructor() {
    private val reqResApi: ReqResApi
    var BASE_URL =Constants.REQRES


    init {
        val retrofit: Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        reqResApi = retrofit.create(ReqResApi::class.java)
    }

    fun getReqResApi(): ReqResApi {
        return reqResApi
    }

    companion object {
        @get:Synchronized
        var instance: ReqResApiClient? = null
            get() {
                if (field == null) {
                    field = ReqResApiClient()
                }
                return field
            }
            private set
    }
}