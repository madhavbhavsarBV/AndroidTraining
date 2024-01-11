package com.mad.androidtraining.aug8retrofit.models

import com.google.gson.annotations.SerializedName
import com.mad.androidtraining.aug8retrofit.helper.Constants

data class Entries(
    @SerializedName("count")
    val count: Int = 0,

    @SerializedName("entries")
    val entries: ArrayList<EntriesList> = arrayListOf(),
)

data class EntriesList(
    @SerializedName(Constants.API)
    val api: String = "",
    @SerializedName(Constants.DESC)
    val description: String = "",
    @SerializedName(Constants.AUTH)
    val auth: String = "",
    @SerializedName(Constants.HTTPS)
    val https: Boolean = false,
    @SerializedName(Constants.CORS)
    val cors: String = "",
    @SerializedName(Constants.LINK)
    val link: String = "",
    @SerializedName(Constants.CATEGORY)
    val category: String = ""
)