package com.arthlimchiu.basicdaggertutorial.ui.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arthlimchiu.basicdaggertutorial.models.Movie
import com.arthlimchiu.basicdaggertutorial.repository.UserRepository

class MainViewModel(private val userRepository: UserRepository) : ViewModel() {

    //saves data during orientation change,has both current data and searched data

    val currentlist:ArrayList<Movie> = arrayListOf()

    fun getList(position:Int) : ArrayList<Movie> {
        val resultlist  = userRepository.getList(position)
        currentlist.addAll(resultlist)
        return resultlist
    }

    fun getsearchlist(text:String) : ArrayList<Movie>
    {
        return ArrayList(currentlist.filter { it.name.contains(text, ignoreCase = true)})
    }
}