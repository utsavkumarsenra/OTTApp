package com.arthlimchiu.basicdaggertutorial

import com.arthlimchiu.basicdaggertutorial.models.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface Api {

    @GET("movie/{movie}")
    fun getUser(@Path("movie") user: String): Call<Movie>
}