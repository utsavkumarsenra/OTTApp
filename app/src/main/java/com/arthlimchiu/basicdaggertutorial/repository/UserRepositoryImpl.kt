package com.arthlimchiu.basicdaggertutorial.repository

import com.arthlimchiu.basicdaggertutorial.models.Movie
import com.arthlimchiu.basicdaggertutorial.util.apijson
import com.google.gson.Gson
import org.json.JSONObject


class UserRepositoryImpl() : UserRepository {

    //geting movie list by pages by parsing json data given in api
    //using gson to convert to model class
    override fun getList(position: Int): ArrayList<Movie> {
        val movieList = ArrayList<Movie>()
        val apijson = apijson()
        val json= JSONObject(apijson.apijson.get(position-1))
        val jsonArray = json.getJSONObject("page").getJSONObject("content-items").getJSONArray("content")
        for (i in 0..jsonArray.length()-1)
        {
            val gson = Gson()
            val thisobject = jsonArray.get(i).toString()
            movieList.add(gson.fromJson(thisobject, Movie::class.java))
        }

        return movieList
    }
}