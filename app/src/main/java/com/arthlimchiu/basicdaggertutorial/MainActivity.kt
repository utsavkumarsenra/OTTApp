package com.arthlimchiu.basicdaggertutorial

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var fullName: TextView
    private lateinit var username: EditText
    private lateinit var search: Button

    @Inject
    lateinit var api: Api

    private lateinit var viewModel: MainViewModel
    private lateinit var factory: MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        component.inject(this)

        factory = MainViewModelFactory(api)
        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)

        fullName = findViewById(R.id.full_name)
        username = findViewById(R.id.username)
        search = findViewById(R.id.search)

        viewModel.fullName.observe(this, Observer { name ->
            fullName.text = name
        })
    }

    override fun onStart() {
        super.onStart()
        search.setOnClickListener {
            viewModel.searchUser(username.text.toString())
        }
    }
}
