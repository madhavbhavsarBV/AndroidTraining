package com.mad.androidtraining.july31retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import com.mad.androidtraining.databinding.ActivityJsonSecondBinding
import com.mad.androidtraining.july31retrofit.models.UserModel
import com.mad.androidtraining.july31retrofit.models.UsersData
import com.mad.androidtraining.july31retrofit.retrofit.RetrofitClient
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class JsonSecondActivity : AppCompatActivity() {

    private lateinit var jsonSecondBinding: ActivityJsonSecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        jsonSecondBinding = ActivityJsonSecondBinding.inflate(layoutInflater)
        setContentView(jsonSecondBinding.root)


        jsonSecondBinding.btnSubmitField.setOnClickListener {
            if (jsonSecondBinding.etFirstName.text.toString().trim().isEmpty()) {
                Toast.makeText(this, "Enter First Name", Toast.LENGTH_SHORT).show()
            } else if (jsonSecondBinding.etLastName.text.toString().trim().isEmpty()) {
                Toast.makeText(this, "Enter Second Name", Toast.LENGTH_SHORT).show()
            } else if (jsonSecondBinding.etJob.text.toString().trim().isEmpty()) {
                Toast.makeText(this, "Enter Job", Toast.LENGTH_SHORT).show()
            } else {

                // calling a method to create a post and passing our modal class.
                val call =
                    RetrofitClient.instance?.getUsersApi()?.putUsersbyFeild(jsonSecondBinding.etFirstName.text.toString().trim() +" "+
                            jsonSecondBinding.etLastName.text.toString().trim(), jsonSecondBinding.etJob.text.toString().trim())

                // on below line we are executing our method.
                call?.enqueue(object : Callback<UserModel?> {
                    override fun onResponse(
                        call: Call<UserModel?>,
                        response: Response<UserModel?>
                    ) {
                        // this method is called when we get response from our api.
                        Toast.makeText(
                            this@JsonSecondActivity,
                            "Data added to API",
                            Toast.LENGTH_SHORT
                        )
                            .show()

                        val responseFromAPI: UserModel? = response.body()

                        // on below line we are getting our data from modal class and adding it to our string.
                        val responseString = """
                            Response Code : ${response.code()}      
                            Name : ${responseFromAPI?.name}
                            Job : ${responseFromAPI?.job}
                        """

                        jsonSecondBinding.tvResult.text = responseString

                        jsonSecondBinding.etFirstName.setText("")
                        jsonSecondBinding.etLastName.setText("")
                        jsonSecondBinding.etJob.setText("")
                    }

                    override fun onFailure(call: Call<UserModel?>, t: Throwable) {

                    }
                })


            }
        }
        jsonSecondBinding.btnSubmit.setOnClickListener {

            if (jsonSecondBinding.etFirstName.text.toString().trim().isEmpty()) {
                Toast.makeText(this, "Enter First Name", Toast.LENGTH_SHORT).show()
            } else if (jsonSecondBinding.etLastName.text.toString().trim().isEmpty()) {
                Toast.makeText(this, "Enter Second Name", Toast.LENGTH_SHORT).show()
            } else if (jsonSecondBinding.etJob.text.toString().trim().isEmpty()) {
                Toast.makeText(this, "Enter Job", Toast.LENGTH_SHORT).show()
            } else {

                val model = UserModel(

                    jsonSecondBinding.etFirstName.text.toString().trim() +" "+
                            jsonSecondBinding.etLastName.text.toString().trim(),
                    jsonSecondBinding.etJob.text.toString().trim()
                )

                // calling a method to create a post and passing our modal class.
                val call: Call<UserModel?>? =
                    RetrofitClient.instance?.getUsersApi()?.putUsersbyModel(model)

                // on below line we are executing our method.
                call?.enqueue(object : Callback<UserModel?> {
                    override fun onResponse(
                        call: Call<UserModel?>,
                        response: Response<UserModel?>
                    ) {
                        // this method is called when we get response from our api.
                        Toast.makeText(
                            this@JsonSecondActivity,
                            "Data added to API",
                            Toast.LENGTH_SHORT
                        )
                            .show()

                        val responseFromAPI: UserModel? = response.body()

                        // on below line we are getting our data from modal class and adding it to our string.
                        val responseString = """
                            Response Code : ${response.code()}      
                            Name : ${responseFromAPI?.name}
                            Job : ${responseFromAPI?.job}
                        """

                        jsonSecondBinding.tvResult.text = responseString

                        jsonSecondBinding.etFirstName.setText("")
                        jsonSecondBinding.etLastName.setText("")
                        jsonSecondBinding.etJob.setText("")



                    }

                    override fun onFailure(call: Call<UserModel?>, t: Throwable) {

                    }
                })


            }



        }
    }



}