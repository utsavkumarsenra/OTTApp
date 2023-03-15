package com.arthlimchiu.basicdaggertutorial.ui.view



import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.arthlimchiu.basicdaggertutorial.MyPagingAdaper
import com.arthlimchiu.basicdaggertutorial.R
import com.arthlimchiu.basicdaggertutorial.component
import com.paging.gridview.FooterViewGridAdapter
import com.paging.gridview.PagingGridView
import com.paging.gridview.PagingGridView.Pagingable
import org.json.JSONObject
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

//    private lateinit var fullName: TextView
//    private lateinit var username: EditText
//    private lateinit var search: Button

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
            if (pager < 3 && currentpage<=3 && !isSearch) {
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
        if (id == com.arthlimchiu.basicdaggertutorial.R.id.searchUSer) {

            // Do something
            return true
        }
        //        if (id == R.id.action_send) {
//
//            // Do something
//            return true;
//        }
        return super.onOptionsItemSelected(item)
    }


}
