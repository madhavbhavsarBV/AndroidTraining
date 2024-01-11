package com.mad.androidtraining.aug8retrofit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.mad.androidtraining.R
import com.mad.androidtraining.aug8retrofit.models.AuthModel
import com.mad.androidtraining.aug8retrofit.retrofit.ReqResApiClient
import com.mad.androidtraining.databinding.FragmentPostLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern


class PostLoginFragment : Fragment() {

    private lateinit var postLoginBinding: FragmentPostLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        postLoginBinding = FragmentPostLoginBinding.inflate(inflater)


        postLoginBinding.btnSubmit.setOnClickListener {
            onSubmitOnClick()
        }


        //custom set email and password
        postLoginBinding.etEmail.setText("eve.holt@reqres.in")
        postLoginBinding.etPassword.setText("cityslicka")
        
        return postLoginBinding.root
    }


    // on submit on click validations
    private fun onSubmitOnClick() {

        if (validateEmail(postLoginBinding.etEmail.text.toString().trim())) {
            Toast.makeText(requireContext(), "Enter Email Properly", Toast.LENGTH_SHORT).show()
        }  else if (validatePassword(postLoginBinding.etPassword.text.toString().trim())) {
            Toast.makeText(requireContext(), "Enter Password", Toast.LENGTH_SHORT).show()
        } else {
            saveSession()
        }
    }

    // validations
    private fun validateEmail(s: String): Boolean {
        val pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}$")
        val matcher = pattern.matcher(s)
        return !(matcher.matches())
    }

    private fun validatePassword(s: String): Boolean {

       return s.isEmpty()
    }

    // save sessions
    private fun saveSession() {
        val model = AuthModel(
            postLoginBinding.etEmail.text.toString().trim(),
            postLoginBinding.etPassword.text.toString().trim()
        )

        // calling a method to create a post and passing our modal class.
        val call: Call<AuthModel?>? =
            ReqResApiClient.instance?.getReqResApi()?.postLogin(model)

        // on below line we are executing our method.
        call?.enqueue(object : Callback<AuthModel?> {
            override fun onResponse(
                call: Call<AuthModel?>,
                response: Response<AuthModel?>
            ) {
                // requireContext() method is called when we get response from our api.
                Toast.makeText(
                    requireContext(),
                    "Data added to API",
                    Toast.LENGTH_SHORT
                )
                    .show()

                val responseFromAPI: AuthModel? = response.body()

                // on below line we are getting our data from modal class and adding it to our string.
                val responseString = """
                            Response Code : ${response.code()}      
                                                  
                        """

                postLoginBinding.tvResult.text = responseString
                postLoginBinding.etEmail.setText("")
                postLoginBinding.etPassword.setText("")


            }

            override fun onFailure(call: Call<AuthModel?>, t: Throwable) {

            }
        })
    }



}