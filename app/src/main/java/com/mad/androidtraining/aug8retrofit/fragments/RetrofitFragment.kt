package com.mad.androidtraining.aug8retrofit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mad.androidtraining.R
import com.mad.androidtraining.databinding.FragmentRetrofitBinding


class RetrofitFragment : Fragment() {

    private lateinit var retrofitBinding: FragmentRetrofitBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        retrofitBinding = FragmentRetrofitBinding.inflate(inflater)


        retrofitBinding.btnGetUsers.setOnClickListener {
            findNavController().navigate(R.id.action_retrofitFragment_to_getUsersFragment)
        }

        retrofitBinding.btnGetEntries.setOnClickListener {
            findNavController().navigate(R.id.action_retrofitFragment_to_getEntriesFragment)
        }


        retrofitBinding.btnPostLogin.setOnClickListener {
            findNavController().navigate(R.id.action_retrofitFragment_to_postLoginFragment)
        }

        retrofitBinding.btnPostUsers.setOnClickListener {
            findNavController().navigate(R.id.action_retrofitFragment_to_postUsersFragment)
        }

        retrofitBinding.btnPostRegister.setOnClickListener {
            findNavController().navigate(R.id.action_retrofitFragment_to_postRegisterFragment)
        }


        return retrofitBinding.root
    }


}