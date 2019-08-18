package com.arthlimchiu.basicdaggertutorial

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var fullName: TextView
    private lateinit var username: EditText
    private lateinit var search: Button

    private lateinit var retrofit: Retrofit
    private lateinit var api: Api

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fullName = findViewById(R.id.full_name)
        username = findViewById(R.id.username)
        search = findViewById(R.id.search)

        retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(Api::class.java)
    }

    override fun onStart() {
        super.onStart()
        search.setOnClickListener {
            searchUser(username.text.toString())
        }
    }

    private fun searchUser(user: String) {
        api.getUser(user).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                response.body()?.let { user ->
                    fullName.text = user.name
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("MainActivity", "onFailure: ", t)
            }
        })
    }
}
