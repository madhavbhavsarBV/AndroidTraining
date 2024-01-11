package com.mad.androidtraining.july31retrofit.models


data class UsersMain (

    val page:Int = 0,
    val per_page:Int = 0,
    val total:Int = 0,
    val total_pages:Int = 0,
    val data:ArrayList<UsersData> = arrayListOf(),
    val support: UsersSupport
)

data class UsersData (
    val id:Int=0,
    val email:String="",
    val first_name:String="",
    val last_name :String="",
    val avatar : String=""
)

data class UsersSupport(
    val url: String="",
    val text: String=""
)
