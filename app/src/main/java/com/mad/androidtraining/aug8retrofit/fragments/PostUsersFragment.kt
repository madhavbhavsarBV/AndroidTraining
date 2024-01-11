package com.mad.androidtraining.aug8retrofit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.mad.androidtraining.aug8retrofit.models.UserModel
import com.mad.androidtraining.aug8retrofit.retrofit.ReqResApiClient
import com.mad.androidtraining.databinding.FragmentPostUsersBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PostUsersFragment : Fragment() {


    private lateinit var postUsersBinding: FragmentPostUsersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for requireContext() fragment
        postUsersBinding = FragmentPostUsersBinding.inflate(inflater)


        // on submit on click listener
        postUsersBinding.btnSubmit.setOnClickListener {
            onSubmitOnClick()
        }


        return postUsersBinding.root
    }

    // submit validations
    private fun onSubmitOnClick() {

        if (postUsersBinding.etFirstName.text.toString().trim().isEmpty()) {
            Toast.makeText(requireContext(), "Enter First Name", Toast.LENGTH_SHORT).show()
        }  else if (postUsersBinding.etJob.text.toString().trim().isEmpty()) {
            Toast.makeText(requireContext(), "Enter Job", Toast.LENGTH_SHORT).show()
        } else {

            saveSession()

        }
    }

    // post sessions
    private fun saveSession() {
        val model = UserModel(
            postUsersBinding.etFirstName.text.toString().trim(),
            postUsersBinding.etJob.text.toString().trim()
        )

        // calling a method to create a post and passing our modal class.
        val call: Call<UserModel?>? =
            ReqResApiClient.instance?.getReqResApi()?.postUsers(model)

        // on below line we are executing our method.
        call?.enqueue(object : Callback<UserModel?> {
            override fun onResponse(
                call: Call<UserModel?>,
                response: Response<UserModel?>
            ) {
                // requireContext() method is called when we get response from our api.
                Toast.makeText(
                    requireContext(),
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

                postUsersBinding.tvResult.text = responseString

                postUsersBinding.etFirstName.setText("")
                postUsersBinding.etJob.setText("")


            }

            override fun onFailure(call: Call<UserModel?>, t: Throwable) {

            }
        })
    }


}