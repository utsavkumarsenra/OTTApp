package com.arthlimchiu.basicdaggertutorial

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val api: Api) : ViewModel() {

    private val _fullName = MutableLiveData<String>()

    val fullName: LiveData<String>
        get() = _fullName

    fun searchUser(user: String) {
        api.getUser(user).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                response.body()?.let { user ->
                    _fullName.value = user.name
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("MainActivity", "onFailure: ", t)
            }
        })
    }
}