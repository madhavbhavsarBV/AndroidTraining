package com.mad.androidtraining.aug8retrofit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mad.androidtraining.R
import com.mad.androidtraining.aug8retrofit.adapter.EntriesRecyclerAdapter
import com.mad.androidtraining.aug8retrofit.adapter.UserRecyclerViewAdapter
import com.mad.androidtraining.aug8retrofit.models.Entries
import com.mad.androidtraining.aug8retrofit.models.EntriesList
import com.mad.androidtraining.aug8retrofit.models.Users
import com.mad.androidtraining.aug8retrofit.retrofit.PublicApiClient
import com.mad.androidtraining.aug8retrofit.retrofit.ReqResApiClient
import com.mad.androidtraining.databinding.FragmentGetEntriesBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GetEntriesFragment : Fragment() {

    private lateinit var getEntriesBinding: FragmentGetEntriesBinding
    private var entriesList = arrayListOf<EntriesList>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        getEntriesBinding = FragmentGetEntriesBinding.inflate(inflater)


        // progress bar
        showProgressBar()


        // getting entries from api
        getEntriesfromApi()
        return getEntriesBinding.root
    }

    private fun showProgressBar() {
        getEntriesBinding.pbLoader.visibility = View.VISIBLE
        getEntriesBinding.rvEntries.visibility = View.GONE
    }

    private fun hideProgressBar() {
        getEntriesBinding.pbLoader.visibility = View.GONE
        getEntriesBinding.rvEntries.visibility = View.VISIBLE
    }

    private fun getEntriesfromApi() {

        val call: Call<Entries>? = PublicApiClient.instance?.getPublicApi()?.getEntries()
        call?.enqueue(object : Callback<Entries> {
            override fun onResponse(call: Call<Entries>, response: Response<Entries>) {
                response.body()?.let {
                    val entries = it

                    Toast.makeText(requireContext(), "${entries.count}", Toast.LENGTH_SHORT).show()

                    for (i in entries.entries){
                        entriesList.add(i)
                    }

                    // setting recycler view
                    getEntriesBinding.rvEntries.adapter = EntriesRecyclerAdapter(requireContext(),entriesList)
                    getEntriesBinding.rvEntries.layoutManager = LinearLayoutManager(requireContext())

                    //hide progress bar when data is retrived
                    hideProgressBar()

                }

            }

            override fun onFailure(call: Call<Entries>, t: Throwable) {
                Toast.makeText(requireContext(), "An error has occured in Entries", Toast.LENGTH_LONG).show()
            }
        })
    }


}