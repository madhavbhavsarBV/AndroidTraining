package com.mad.androidtraining.aug8retrofit.models

import com.google.gson.annotations.SerializedName
import com.mad.androidtraining.aug8retrofit.helper.Constants

data class AuthModel(

    @SerializedName(Constants.EMAIL)
    val email:String = "",

    @SerializedName(Constants.PASSWORD)
    val password:String = ""

)
