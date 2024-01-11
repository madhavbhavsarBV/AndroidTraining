package com.mad.androidtraining.aug8retrofit.models

import com.google.gson.annotations.SerializedName
import com.mad.androidtraining.aug8retrofit.helper.Constants

data class UserModel (
    @SerializedName(Constants.NAME)
    val name:String = "",
    @SerializedName(Constants.JOB)
    val job:String = ""
)