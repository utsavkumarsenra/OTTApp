package com.arthlimchiu.basicdaggertutorial

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.arthlimchiu.basicdaggertutorial.models.Movie
import com.paging.gridview.PagingBaseAdapter


class MyPagingAdaper : PagingBaseAdapter<Movie>() {
    private lateinit var name: TextView
    private lateinit var movieImage: ImageView
    private lateinit var context:Context
    private var layoutInflater: LayoutInflater? = null

    fun setContext(contextin : Context)
    {
        context = contextin
    }

    override fun getCount(): Int {
        return items.size
    }



    override fun getItem(position: Int): Movie? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

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
        name.setText(items.get(position).name)
        val postername:String = items.get(position).posterimage.substring(0,items.get(position).posterimage.indexOf('.'))


        val id: Int =
            context.getResources().getIdentifier("com.arthlimchiu.basicdaggertutorial:drawable/"+postername, null, null)

        try {

            // on below line we are setting image for our course image view
            movieImage.setImageResource(id)
        } catch (e: Exception) {
            movieImage.setImageResource(R.drawable.placeholder_for_missing_posters)
            e.printStackTrace()
        }


        return convertView
    }
}