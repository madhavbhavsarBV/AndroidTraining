package com.mad.androidtraining.july31retrofit

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mad.androidtraining.databinding.ActivityJsonBinding
import com.mad.androidtraining.july31retrofit.adapter.UserRecyclerViewAdapter
import com.mad.androidtraining.july31retrofit.models.UsersData
import com.mad.androidtraining.july31retrofit.models.UsersMain
import com.mad.androidtraining.july31retrofit.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class JsonActivity : AppCompatActivity() {

    private lateinit var jsonBinding: ActivityJsonBinding
    var userDataList: ArrayList<UsersData> = arrayListOf()
    var userNameList: ArrayList<String> = arrayListOf("1","2")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        jsonBinding = ActivityJsonBinding.inflate(layoutInflater)
        setContentView(jsonBinding.root)


        getUsersMain()


//        jsonBinding.rvUsers.adapter = UserRecyclerViewAdapter(this@JsonActivity,userDataList)
//        jsonBinding.rvUsers.layoutManager = LinearLayoutManager(this@JsonActivity)


        jsonBinding.actvPage.setOnItemClickListener { adapterView, view, i, l ->

            val pageNo = jsonBinding.actvPage.text.toString().trim().toString()
            val call: Call<UsersMain>? = RetrofitClient.instance?.getUsersApi()?.getUsersbyPage(pageNo.toInt())
            call?.enqueue(object : Callback<UsersMain> {
                override fun onResponse(call: Call<UsersMain>, response: Response<UsersMain>) {
                    response.body()?.let {
                        val usersMain = it
                        userDataList = arrayListOf()
                        for (i in usersMain.data){
                            userDataList.add(i)
                        }
                        setDropDown()

                        jsonBinding.rvUsers.adapter = UserRecyclerViewAdapter(this@JsonActivity,userDataList)
                        jsonBinding.rvUsers.layoutManager = LinearLayoutManager(this@JsonActivity)

                    }

                }

                override fun onFailure(call: Call<UsersMain>, t: Throwable) {
                    Toast.makeText(applicationContext, "An error has occured", Toast.LENGTH_LONG).show()
                }
            })


        }

        jsonBinding.fabAdd.setOnClickListener {
            startActivity(Intent(this, JsonSecondActivity::class.java))
        }

    }

    private fun setDropDown() {
        var adapterNames = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,userNameList)
        jsonBinding.actvPage.setAdapter(adapterNames)
    }

    private fun getUsersMain() {
        val call: Call<UsersMain>? = RetrofitClient.instance?.getUsersApi()?.getUsers()
        call?.enqueue(object : Callback<UsersMain> {
            override fun onResponse(call: Call<UsersMain>, response: Response<UsersMain>) {
                response.body()?.let {
                    val usersMain = it


                    for (i in usersMain.data){
                        userDataList.add(i)
                    }
                    setDropDown()

                    jsonBinding.rvUsers.adapter = UserRecyclerViewAdapter(this@JsonActivity,userDataList)
                    jsonBinding.rvUsers.layoutManager = LinearLayoutManager(this@JsonActivity)

                }

            }

            override fun onFailure(call: Call<UsersMain>, t: Throwable) {
                Toast.makeText(applicationContext, "An error has occured", Toast.LENGTH_LONG).show()
            }
        })
    }
}