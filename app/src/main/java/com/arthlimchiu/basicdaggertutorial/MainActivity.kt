package com.arthlimchiu.basicdaggertutorial


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.arthlimchiu.basicdaggertutorial.models.Movie
import com.paging.gridview.FooterViewGridAdapter
import com.paging.gridview.PagingGridView
import com.paging.gridview.PagingGridView.Pagingable
import org.json.JSONObject
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

//    private lateinit var fullName: TextView
//    private lateinit var username: EditText
//    private lateinit var search: Button

    private lateinit var grid:com.paging.gridview.PagingGridView

    @Inject
    lateinit var factory: MainViewModelFactory

    private lateinit var viewModel: MainViewModel

    private var pager = 0

    private var currentpage=1;

    lateinit var gridadapter:MyPagingAdaper

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


        // on below line we are setting adapter to our grid view.


        grid.setHasMoreItems(true)
        grid.setPagingableListener(Pagingable {
            if (pager < 3 && currentpage<=3) {
                currentpage++
                grid.onFinishLoading(true, if (currentpage==2)
                    getSecondList()
                else
                    getThirdList());
            } else {
                grid.onFinishLoading(false, null)
            }
        })

        setgridadapter()





    }

    override fun onStart() {
        super.onStart()
//        search.setOnClickListener {
//            viewModel.searchUser(username.text.toString())
//        }
    }

    fun setgridadapter()
    {
        val movieList = ArrayList<Movie>()
        val apijson = apijson()
        val json1 = JSONObject(apijson.json1)
        val jsonArray = json1.getJSONObject("page").getJSONObject("content-items").getJSONArray("content")
        for (i in 0..jsonArray.length()-1)
        {
            val thisobject = jsonArray.get(i) as JSONObject
            movieList.add(Movie(thisobject.getString("name"),thisobject.getString("poster-image")))
        }
        gridadapter = MyPagingAdaper()

        grid.adapter = gridadapter
        gridadapter.setContext(this@MainActivity)
        grid.onFinishLoading(true, movieList);
    }

    fun getSecondList():ArrayList<Movie>
    {
        val movieList = ArrayList<Movie>()
        val apijson = apijson()
        val json2 = JSONObject(apijson.json2)
        val jsonArray = json2.getJSONObject("page").getJSONObject("content-items").getJSONArray("content")
        for (i in 0..jsonArray.length()-1)
        {
            val thisobject = jsonArray.get(i) as JSONObject
            movieList.add(Movie(thisobject.getString("name"),thisobject.getString("poster-image")))
        }

        return movieList
    }

    fun getThirdList():ArrayList<Movie>
    {
        val movieList = ArrayList<Movie>()
        val apijson = apijson()
        val json3= JSONObject(apijson.json3)
        val jsonArray = json3.getJSONObject("page").getJSONObject("content-items").getJSONArray("content")
        for (i in 0..jsonArray.length()-1)
        {
            val thisobject = jsonArray.get(i) as JSONObject
            movieList.add(Movie(thisobject.getString("name"),thisobject.getString("poster-image")))
        }

        return movieList
    }

    private fun clearData() {
        if (grid.getAdapter() != null) {
            pager = 0
            gridadapter =
                (grid.getAdapter() as FooterViewGridAdapter).wrappedAdapter as MyPagingAdaper
            gridadapter.removeAllItems()

            grid = findViewById<View>(R.id.moviesGrid) as PagingGridView
            gridadapter = MyPagingAdaper()
        }
    }


}
