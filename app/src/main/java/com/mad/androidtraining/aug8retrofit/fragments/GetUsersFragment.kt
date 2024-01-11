package com.mad.androidtraining.aug8retrofit.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mad.androidtraining.R
import com.mad.androidtraining.aug8retrofit.adapter.UserRecyclerViewAdapter
import com.mad.androidtraining.aug8retrofit.models.Users
import com.mad.androidtraining.aug8retrofit.models.UsersData
import com.mad.androidtraining.aug8retrofit.retrofit.ReqResApiClient
import com.mad.androidtraining.databinding.FragmentGetUsersBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GetUsersFragment : Fragment() {

    private lateinit var getUsersBinding: FragmentGetUsersBinding
    var userDataList: ArrayList<UsersData> = arrayListOf()
    var userNameList: ArrayList<String> = arrayListOf("1","2")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getUsersBinding = FragmentGetUsersBinding.inflate(inflater)

        // getting users main object and setting insde data to recycler view
        getUsers()

        // show progress bar when data is not loaded
        showProgressBar()

        // on changing page from 1 to 2
        getUsersBinding.actvPage.setOnItemClickListener { adapterView, view, i, l ->
            onPageChange()
        }

        return getUsersBinding.root
    }



    private fun onPageChange() {

        //getting page number
        val pageNo = getUsersBinding.actvPage.text.toString().trim().toString()

        // calling retrofit client of req res api
        val call: Call<Users>? = ReqResApiClient.instance?.getReqResApi()?.getUsersbyPage(pageNo.toInt())
        call?.enqueue(object : Callback<Users> {
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                response.body()?.let {
                    val users = it
                    userDataList = arrayListOf<UsersData>()
                    for (i in users.data){
                        userDataList.add(i)
                    }
                    setDropDown()

                    getUsersBinding.rvUsers.adapter = UserRecyclerViewAdapter(requireContext(),userDataList)
                    getUsersBinding.rvUsers.layoutManager = LinearLayoutManager(requireContext())

                }

            }

            override fun onFailure(call: Call<Users>, t: Throwable) {
                Toast.makeText(requireContext(), "An error has occured", Toast.LENGTH_LONG).show()
            }
        })

    }


    // set drop down of page number
    private fun setDropDown() {
        val adapterNames = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,userNameList)
        getUsersBinding.actvPage.setAdapter(adapterNames)
    }

    private fun showProgressBar() {
        getUsersBinding.pbLoader.visibility = View.VISIBLE
        getUsersBinding.rvUsers.visibility = View.GONE
    }

    private fun hideProgressBar() {
        getUsersBinding.pbLoader.visibility = View.GONE
        getUsersBinding.rvUsers.visibility = View.VISIBLE
    }

    private fun getUsers() {
        val call: Call<Users>? = ReqResApiClient.instance?.getReqResApi()?.getUsers()
        call?.enqueue(object : Callback<Users> {
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                response.body()?.let {
                    val users = it

                    for (i in users.data){
                        userDataList.add(i)
                    }
                    //setting drop down in spinner
                    setDropDown()

                    // setting recycler view
                    getUsersBinding.rvUsers.adapter = UserRecyclerViewAdapter(requireContext(),userDataList)
                    getUsersBinding.rvUsers.layoutManager = LinearLayoutManager(requireContext())

                    // hide progress bar
                    hideProgressBar()

                }

            }

            override fun onFailure(call: Call<Users>, t: Throwable) {
                Toast.makeText(requireContext(), "An error has occured", Toast.LENGTH_LONG).show()
            }
        })
    }

}