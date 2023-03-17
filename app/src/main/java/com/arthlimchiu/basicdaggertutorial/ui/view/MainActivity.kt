package com.arthlimchiu.basicdaggertutorial.ui.view




import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.arthlimchiu.basicdaggertutorial.MyPagingAdaper
import com.arthlimchiu.basicdaggertutorial.R
import com.arthlimchiu.basicdaggertutorial.component
import com.paging.gridview.FooterViewGridAdapter
import com.paging.gridview.PagingGridView
import com.paging.gridview.PagingGridView.Pagingable
import javax.inject.Inject


class MainActivity : AppCompatActivity() {



    private lateinit var grid:PagingGridView

    @Inject
    lateinit var factory: MainViewModelFactory

    private lateinit var viewModel: MainViewModel

    private var pager = 0

    private var currentpage=1;

    lateinit var gridadapter: MyPagingAdaper

    private var isSearch = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        component.inject(this)

        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        grid = findViewById(R.id.moviesGrid)



        grid.setHasMoreItems(true)

        //setting pagination listener for pagination
        grid.setPagingableListener(Pagingable {
            if (pager < 3 && currentpage<3 && !isSearch) {
                currentpage++
                grid.onFinishLoading(true, if (currentpage==2)
                    viewModel.getList(2)
                else
                    viewModel.getList(3))
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

        gridadapter = MyPagingAdaper()
        grid.adapter = gridadapter
        gridadapter.setContext(this@MainActivity)
        grid.onFinishLoading(true, viewModel.getList(1))

        supportActionBar?.title = resources.getString(R.string.movietype)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val d = resources.getDrawable(R.drawable.nav_bar)
        supportActionBar?.setBackgroundDrawable(d)





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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        val myActionMenuItem: MenuItem? = menu?.findItem(R.id.searchUSer)
        val searchView: SearchView = myActionMenuItem?.actionView as SearchView

        searchView.setQueryHint("Search Movies");

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if (TextUtils.isEmpty(newText)) {
                    isSearch = false
//                    adapter.filter("")
//                    listView.clearTextFilter()
                    clearData()
                    grid.onFinishLoading(true, viewModel.currentlist)



                } else if (newText?.length!! >2)  {
                    isSearch = true
                    clearData()
                    val newlist = viewModel.getsearchlist(newText)
                    clearData()
                    grid.onFinishLoading(true, newlist)
//                    adapter.filter(newText)
                }




                return true
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id: Int = item.getItemId()
        if (id ==R.id.searchUSer) {

            // Do something
            return true
        }

        else if (id ==android.R.id.home) {
            finish()
            // Do something
            return true
        }

        return super.onOptionsItemSelected(item)
    }


}
