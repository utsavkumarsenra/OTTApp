package com.arthlimchiu.basicdaggertutorial


import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView


// on below line we are creating an
// adapter class for our grid view.
internal class GridAdapter(
    // on below line we are creating two
    // variables for course list and context
    private val movieList: List<Movie>,
    private val context: Context
) :
    BaseAdapter() {
    // in base adapter class we are creating variables
    // for layout inflater, course image view and course text view.
    private var layoutInflater: LayoutInflater? = null
    private lateinit var name: TextView
    private lateinit var movieImage: ImageView

    // below method is use to return the count of course list
    override fun getCount(): Int {
        return movieList.size
    }

    // below function is use to return the item of grid view.
    override fun getItem(position: Int): Any? {
        return null
    }

    // below function is use to return item id of grid view.
    override fun getItemId(position: Int): Long {
        return 0
    }

    // in below function we are getting individual item of grid view.
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        // on blow line we are checking if layout inflater
        // is null, if it is null we are initializing it.
        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        // on the below line we are checking if convert view is null.
        // If it is null we are initializing it.
        if (convertView == null) {
            // on below line we are passing the layout file
            // which we have to inflate for each item of grid view.
            convertView = layoutInflater!!.inflate(R.layout.singleportaititem, null)
        }
        // on below line we are initializing our course image view
        // and course text view with their ids.
        name = convertView!!.findViewById(R.id.movieName)
        movieImage = convertView!!.findViewById(R.id.movieImage)
        // on below line we are setting text in our course text view.
        name.setText(movieList.get(position).name)
        val postername:String = movieList.get(position).posterimage.substring(0,movieList.get(position).posterimage.indexOf('.'))


        val id: Int =
            context.getResources().getIdentifier("com.arthlimchiu.basicdaggertutorial:drawable/"+postername, null, null)

        try {

            // on below line we are setting image for our course image view
            movieImage.setImageResource(id)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return convertView





    }
}
