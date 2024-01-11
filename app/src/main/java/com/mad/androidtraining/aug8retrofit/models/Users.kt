package com.mad.androidtraining.aug8retrofit.models

import com.google.gson.annotations.SerializedName
import com.mad.androidtraining.aug8retrofit.helper.Constants

data class Users(

    @SerializedName(Constants.PAGE)
    val page: Int = 0,

    @SerializedName(Constants.PER_PAGE)
    val per_page: Int = 0,

    @SerializedName(Constants.TOTAL)
    val total: Int = 0,

    @SerializedName(Constants.TOTAL_PAGES)
    val total_pages: Int = 0,

    @SerializedName(Constants.DATA)
    val data: ArrayList<UsersData> = arrayListOf(),

    @SerializedName(Constants.SUPPORT)
    val support: UsersSupport
)

data class UsersData(

    @SerializedName(Constants.ID)
    val id: Int = 0,

    @SerializedName(Constants.EMAIL)
    val email: String = "",

    @SerializedName(Constants.FIRST_NAME)
    val first_name: String = "",

    @SerializedName(Constants.LAST_NAME)
    val last_name: String = "",

    @SerializedName(Constants.AVATAR)
    val avatar: String = ""
)

data class UsersSupport(

    @SerializedName("url")
    val url: String = "",

    @SerializedName("text")
    val text: String = ""
)
