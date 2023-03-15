package com.arthlimchiu.basicdaggertutorial.repository

import com.arthlimchiu.basicdaggertutorial.models.Movie
import com.arthlimchiu.basicdaggertutorial.util.apijson
import org.json.JSONObject


class UserRepositoryImpl() : UserRepository {



    override fun getList(position: Int): ArrayList<Movie> {
        val movieList = ArrayList<Movie>()
        val apijson = apijson()
        val json= JSONObject(apijson.apijson.get(position-1))
        val jsonArray = json.getJSONObject("page").getJSONObject("content-items").getJSONArray("content")
        for (i in 0..jsonArray.length()-1)
        {
            val thisobject = jsonArray.get(i) as JSONObject
            movieList.add(Movie(thisobject.getString("name"),thisobject.getString("poster-image")))
        }

        return movieList
    }
}