package com.arthlimchiu.basicdaggertutorial

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _fullName = MutableLiveData<String>()

    val fullName: LiveData<String>
        get() = _fullName

    fun searchUser(username: String) {
        userRepository.getUser(
            username,
            { user -> _fullName.value = user.name },
            { t -> Log.e("MainActivity", "onFailure: ", t) }
        )
    }
}