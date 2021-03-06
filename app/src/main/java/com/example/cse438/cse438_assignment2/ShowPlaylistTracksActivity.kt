package com.example.cse438.cse438_assignment2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.cse438_assignment2.Adapter.MoveItemListener
import com.example.cse438.cse438_assignment2.Adapter.PlayListAdapter
import com.example.cse438.cse438_assignment2.Adapter.TrackInPlayListAdapter
import com.example.cse438.cse438_assignment2.Adapter.onDrag
import com.example.cse438.cse438_assignment2.Data.Track
import com.example.cse438.cse438_assignment2.Database.PlayList
import com.example.cse438.cse438_assignment2.Database.TrackList
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_show_playlist.*

import kotlinx.android.synthetic.main.playlist_layout.*
import kotlinx.android.synthetic.main.trackinplaylist_layout.*
import java.util.*
import kotlin.collections.ArrayList

class ShowPlaylistTracksActivity : AppCompatActivity(), onDrag {

    var playlistGenre: String? = ""
    var rating: Int? = 0

    private var trackListViewModel : TrackListViewModel? = null
    private var  listplayList: ArrayList<PlayList> = ArrayList<PlayList>()
    private var returnTracks: ArrayList<TrackList> = ArrayList()

    lateinit var adapter: TrackInPlayListAdapter
    lateinit var onTouch: ItemTouchHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_playlist)
    }

    override fun onStart() {
        super.onStart()

        //store the intent value
        var bundle: Bundle? = intent.extras

        playlistGenre=bundle!!.getString("genre")
        rating=bundle!!.getInt("rating")!!
        val playlistid = bundle!!.getInt("id")!!

        val recyclerView = chooseplaylist_recycler_view
        adapter = TrackInPlayListAdapter(returnTracks, this)
        val called: ItemTouchHelper.Callback = MoveItemListener(adapter)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        onTouch = ItemTouchHelper(called)
        onTouch.attachToRecyclerView(recyclerView)


        //set view model
        trackListViewModel = ViewModelProvider(this).get(TrackListViewModel::class.java)

        trackListViewModel!!.gettracksinplaylist(playlistid).observe(this, Observer { returnTrack ->
            returnTracks.clear()
            returnTracks.addAll(returnTrack)
            Toast.makeText(this, "There are " + returnTracks.size.toString() + " movies in this watchlist.", Toast.LENGTH_SHORT).show()
            adapter.notifyDataSetChanged()
        })

    }

    override fun dragItem(viewHolder: RecyclerView.ViewHolder) {
        onTouch.startDrag(viewHolder)
    }


    fun backtohome(view: View) {
        val intent = Intent(this, MainpageActivity::class.java)
        startActivity(intent)
    }

}
