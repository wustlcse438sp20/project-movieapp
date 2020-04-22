package com.example.cse438.cse438_assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.cse438.cse438_assignment2.Database.TrackList

class AddtoPlaylistActivity : AppCompatActivity() {

    private var moviename : String = ""
    private var playlistid: Int = 0
    private var playlistname : String = ""
    private var trackListViewModel : TrackListViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent.extras
        playlistname = intent!!.getString("playlistname", "")
        moviename = intent!!.getString("moviename", "")
        playlistid = intent!!.getInt("playlistid", 0)

        trackListViewModel = ViewModelProvider(this).get(TrackListViewModel::class.java)

        if(moviename == "" || playlistname == ""){
            Toast.makeText(this, "Added Unsucessful!", Toast.LENGTH_SHORT).show()
        } else {
            trackListViewModel!!.insert(TrackList(playlistname, moviename, playlistid))
            Toast.makeText(this, playlistid.toString() + ": Added to " + playlistname + " Successful!" , Toast.LENGTH_SHORT).show()
        }

        val intent1 = Intent(this, MainpageActivity::class.java)
        startActivity(intent1)
    }
}


