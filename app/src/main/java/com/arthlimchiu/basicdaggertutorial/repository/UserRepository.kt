package com.arthlimchiu.basicdaggertutorial.repository

import com.arthlimchiu.basicdaggertutorial.models.Movie


interface UserRepository {


    fun getList(position: Int) : ArrayList<Movie>


}