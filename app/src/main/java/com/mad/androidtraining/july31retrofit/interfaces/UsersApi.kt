package com.mad.androidtraining.july31retrofit.interfaces

import com.mad.androidtraining.july31retrofit.models.UserModel
import com.mad.androidtraining.july31retrofit.models.UsersMain
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UsersApi {

    @GET("users")
    fun getUsers() : Call<UsersMain>


    @GET("users")
    fun getUsersbyPage(
        @Query("page") page:Int,
    ) : Call<UsersMain>

    @POST("users")
    fun putUsersbyMap(@Body body: Map<String, Any>): Call<UserModel>

    @POST("users")
    fun putUsersbyModel(@Body userModel: UserModel): Call<UserModel?>

    @POST("users")
    fun putUsersbyFeild(
        @Field("name") name:String,
        @Field("job") job:String
    ): Call<UserModel?>

}