package com.example.cse438.cse438_assignment2

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.cse438_assignment2.Adapter.PlayListAdapter2
import com.example.cse438.cse438_assignment2.Database.PlayList
import kotlinx.android.synthetic.main.activity_show_playlist.*
import kotlinx.android.synthetic.main.add_new_playlist.view.*
import kotlinx.android.synthetic.main.fragment_playlist.*


class ChoosePlaylistActivity : AppCompatActivity() {
    private var  listplayList: ArrayList<PlayList> = ArrayList<PlayList>()
<<<<<<< HEAD
    private var playListViewModel : PlayListViewModel2? = null
//    private var trackname : String = ""
//    private var artist : String = ""
//    private var duration: Int = 0
    private var moviename : String = ""
=======
    private var playListViewModel : PlayListViewModel? = null
    private var trackname : String = ""
    private var artist : String = ""
    private var duration: Int = 0
>>>>>>> 36c2578ffe0a312140bc528276abf6001a7ede53

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.playlist_to_choose)
        val intent = intent.extras
//        trackname = intent!!.getString("trackname", "")
//        artist = intent!!.getString("artist", "")
//        duration = intent!!.getInt("duration", 0)
        moviename = intent!!.getString("moviename","")
    }

    override fun onStart() {
        super.onStart()

        //set recycler view
        val recyclerView = chooseplaylist_recycler_view
        val adapter = PlayListAdapter2(listplayList, moviename)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //set view model
        playListViewModel = ViewModelProvider(this).get(PlayListViewModel::class.java)

        //observe the allEvents LiveData
        playListViewModel!!.allPlaylists.observe(this, Observer { playlists ->
            // Update the cached copy of the words in the adapter.
            listplayList.clear()
            listplayList.addAll(playlists)
            adapter.notifyDataSetChanged()
        })
    }

}



