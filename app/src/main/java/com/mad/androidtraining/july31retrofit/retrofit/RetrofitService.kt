package com.mad.androidtraining.july31retrofit.retrofit

import com.mad.androidtraining.july31retrofit.interfaces.UsersApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient private constructor() {
    private val usersApi: UsersApi
    var BASE_URL = "https://reqres.in/api/"


    init {
        val retrofit: Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        usersApi = retrofit.create(UsersApi::class.java)
    }

    fun getUsersApi(): UsersApi {
        return usersApi
    }

    companion object {
        @get:Synchronized
        var instance: RetrofitClient? = null
            get() {
                if (field == null) {
                    field = RetrofitClient()
                }
                return field
            }
            private set
    }
}