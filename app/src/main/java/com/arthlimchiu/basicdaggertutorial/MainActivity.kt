package com.arthlimchiu.basicdaggertutorial

import android.os.Bundle
import android.util.Log
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import java.lang.reflect.Type
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

//    private lateinit var fullName: TextView
//    private lateinit var username: EditText
//    private lateinit var search: Button

    private lateinit var grid:GridView

    @Inject
    lateinit var factory: MainViewModelFactory

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        component.inject(this)

        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        grid = findViewById(R.id.moviesGrid)
//
//        fullName = findViewById(R.id.full_name)
//        username = findViewById(R.id.username)
//        search = findViewById(R.id.search)
//
//        viewModel.fullName.observe(this, Observer { name ->
//            fullName.text = name
//        })

        // on below line we are initializing our course adapter
        // and passing course list and context.
        val movieList = ArrayList<Movie>()
        val apijson = apijson()
        val json1 = JSONObject(apijson.json1)
        val jsonArray = json1.getJSONObject("page").getJSONObject("content-items").getJSONArray("content")
        for (i in 0..jsonArray.length()-1)
        {
            val thisobject = jsonArray.get(i) as JSONObject
            movieList.add(Movie(thisobject.getString("name"),thisobject.getString("poster-image")))
        }
        val courseAdapter = GridAdapter(movieList,this@MainActivity)

        // on below line we are setting adapter to our grid view.
        grid.adapter = courseAdapter



    }

    override fun onStart() {
        super.onStart()
//        search.setOnClickListener {
//            viewModel.searchUser(username.text.toString())
//        }
    }


}
