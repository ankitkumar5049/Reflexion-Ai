package com.example.bookstore.fragment

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.bookstore.R
import com.example.bookstore.adapter.DashboardRecyclerAdapter
import com.example.bookstore.model.Movie
import com.example.bookstore.util.ConnectionManager
import org.json.JSONException
import java.util.*
import kotlin.Comparator


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DashboardFragment : Fragment() {

    lateinit var recyclerDashboard : RecyclerView
    lateinit var layoutManager : RecyclerView.LayoutManager
    lateinit var progressLayout : RelativeLayout
    lateinit var progressBar : ProgressBar

    val movieInfoList = arrayListOf<Movie>()


    var ratingComparator = Comparator<Movie> { movie1,movie2 ->
        if(movie1.rating.compareTo(movie2.rating,true)==0){
            movie1.title.compareTo(movie2.title,true)
        }else {
            movie1.rating.compareTo(movie2.rating, true)
        }
    }

    lateinit var recyclerAdapter: DashboardRecyclerAdapter



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        setHasOptionsMenu(true)

        recyclerDashboard = view.findViewById(R.id.recycleDashboard)
        layoutManager = LinearLayoutManager(activity)
        progressLayout = view.findViewById(R.id.progressLayout)
        progressBar = view.findViewById(R.id.progressBar)
        progressLayout.visibility = View.VISIBLE


        if (ConnectionManager().checkConnection(activity as Context)){
            api()
            api2()
        }
        else{

            val dialog = AlertDialog.Builder(activity as Context)
            dialog.setTitle("Error")
            dialog.setMessage("Internet Connection Not Found")
            dialog.setPositiveButton("Open Settings"){ text, listener->
                val settingIntent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                startActivity(settingIntent)
                activity?.finish()
            }
            dialog.setNegativeButton("Exit"){text,listener->
                ActivityCompat.finishAffinity(activity as Activity)
            }
            dialog.create()
            dialog.show()

        }
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater?.inflate(R.menu.menu_dashboard,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item?.itemId
        if(id==R.id.action_sort){
            Collections.sort(movieInfoList,ratingComparator)
            movieInfoList.reverse()
        }

        recyclerAdapter.notifyDataSetChanged()
        return super.onOptionsItemSelected(item)
    }

    fun api() {
        val queue = Volley.newRequestQueue(activity as Context)

        val url1 = "https://task.auditflo.in/1.json"

        if (ConnectionManager().checkConnection(activity as Context)) {
            val jsonObjectRequest =
                object : JsonObjectRequest(Request.Method.GET, url1, null, Response.Listener {

                    try {
                        if (true) {
                            progressLayout.visibility = View.GONE
                            val data = it.getJSONArray("Movie List")
                            Log.e("TAG", "api1: "+data, null)
                            for (i in 0 until data.length()) {
                                val movieJsonObject = data.getJSONObject(i)
                                val movieObject = Movie(
                                    movieJsonObject.getString("Title"),
                                    movieJsonObject.getString("Year"),
                                    movieJsonObject.getString("Summary"),
                                    movieJsonObject.getString("Short Summary"),
                                    movieJsonObject.getString("Genres"),
                                    movieJsonObject.getString("Runtime"),
                                    movieJsonObject.getString("Rating"),
                                    movieJsonObject.getString("Movie Poster"),
                                    movieJsonObject.getString("Director"),
                                    movieJsonObject.getString("Writers"),
                                    movieJsonObject.getString("Cast"),
                                    movieJsonObject.getString("IMDBID")
                                )

                                movieInfoList.add(movieObject)
                                recyclerAdapter = DashboardRecyclerAdapter(activity as Context,movieInfoList)

                                recyclerDashboard.adapter = recyclerAdapter

                                recyclerDashboard.layoutManager = layoutManager

                            }
                        } else {
                            Toast.makeText(
                                activity as Context,
                                "Some Error Occurred",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    } catch (e: JSONException) {
                        Toast.makeText(
                            activity as Context,
                            "Some unexpected error occurred !",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }, Response.ErrorListener {

                    Log.e("TAG", "api1: "+it, null)
                    //Here we will handle the error
                    if (activity != null) {
                        Toast.makeText(
                            activity as Context,
                            "Volley error occurred !",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }) {
                }

            queue.add(jsonObjectRequest)
        }

    }

    fun api2() {
        progressLayout.visibility = View.VISIBLE
        val queue = Volley.newRequestQueue(activity as Context)

        val url1 = "https://task.auditflo.in/2.json"

        if (ConnectionManager().checkConnection(activity as Context)) {
            val jsonObjectRequest =
                object : JsonObjectRequest(Request.Method.GET, url1, null, Response.Listener {

                    try {
                        if (true) {
                            progressLayout.visibility = View.GONE
                            val data = it.getJSONArray("Movie List")
                            Log.e("TAG", "api1: "+data, null)
                            for (i in 0 until data.length()) {
                                val movieJsonObject = data.getJSONObject(i)
                                val movieObject = Movie(
                                    movieJsonObject.getString("Title"),
                                    movieJsonObject.getString("Year"),
                                    movieJsonObject.getString("Summary"),
                                    movieJsonObject.getString("Short Summary"),
                                    movieJsonObject.getString("Genres"),
                                    movieJsonObject.getString("Runtime"),
                                    movieJsonObject.getString("Rating"),
                                    movieJsonObject.getString("Movie Poster"),
                                    movieJsonObject.getString("Director"),
                                    movieJsonObject.getString("Writers"),
                                    movieJsonObject.getString("Cast"),
                                    movieJsonObject.getString("IMDBID")
                                )

                                movieInfoList.add(movieObject)
                                recyclerAdapter = DashboardRecyclerAdapter(activity as Context,movieInfoList)

                                recyclerDashboard.adapter = recyclerAdapter

                                recyclerDashboard.layoutManager = layoutManager

                            }
                        } else {
                            Toast.makeText(
                                activity as Context,
                                "Some Error Occurred",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    } catch (e: JSONException) {
                        Toast.makeText(
                            activity as Context,
                            "Some unexpected error occurred !",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }, Response.ErrorListener {

                    Log.e("TAG", "api1: "+it, null)
                    //Here we will handle the error
                    if (activity != null) {
                        Toast.makeText(
                            activity as Context,
                            "Volley error occurred !",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }) {
                }

            queue.add(jsonObjectRequest)
        }

    }
}