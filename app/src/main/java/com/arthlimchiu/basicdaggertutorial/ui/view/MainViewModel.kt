package com.arthlimchiu.basicdaggertutorial.ui.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arthlimchiu.basicdaggertutorial.models.Movie
import com.arthlimchiu.basicdaggertutorial.repository.UserRepository

class MainViewModel(private val userRepository: UserRepository) : ViewModel() {



    fun getList(position:Int) : ArrayList<Movie> {
        return userRepository.getList(position)
    }
}