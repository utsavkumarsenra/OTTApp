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

    private var userRepository: UserRepository = UserRepositoryImpl(api)

    fun searchUser(username: String) {
        userRepository.getUser(
            username,
            { user -> _fullName.value = user.name },
            { t -> Log.e("MainActivity", "onFailure: ", t) }
        )
    }
}