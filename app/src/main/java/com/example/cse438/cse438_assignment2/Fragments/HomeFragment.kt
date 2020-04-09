package com.example.cse438.cse438_assignment2.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.cse438_assignment2.Adapter.TrackListAdapter
import com.example.cse438.cse438_assignment2.Data.Track
import com.example.cse438.cse438_assignment2.R
import com.example.cse438_rest_studio.TrackViewModel
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    lateinit var trackViewModel: TrackViewModel
    lateinit var searchButton: SearchView
    lateinit var searchBox: EditText
    lateinit var imageView: ImageView

    var trackList: ArrayList<Track> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        trackViewModel = ViewModelProviders.of(this).get(TrackViewModel::class.java)
        //set recycler view
        val recyclerView = getView()!!.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = TrackListAdapter(trackList as ArrayList<Track>)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this.context, 2)


        //observe the allEvents LiveData
        trackViewModel!!.trackList.observe(this, Observer {
            trackList.clear()
            trackList.addAll(it.data)
            adapter.notifyDataSetChanged()
        })

        trackViewModel.getChart()
        trackViewModel!!.chartList.observe(this, Observer {
            trackList.clear()
            trackList.addAll(it.tracks.data)
            adapter.notifyDataSetChanged()
        })


        search_button.setOnClickListener {
            val input: String = search_box.text.toString()
            trackViewModel.getTrackBySearch(input)


        }


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


}

