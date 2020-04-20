package com.example.cse438.cse438_assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.cse438.cse438_assignment2.Database.TrackList

class AddtoPlaylistActivity : AppCompatActivity() {

    private var trackname : String = ""
    private var artist : String = ""
    private var duration: Int = 0
    private var playlistid: Int = 0
    private var playlistname : String = ""
    private var trackListViewModel : TrackListViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent.extras
        playlistname = intent!!.getString("playlistname", "")
        trackname = intent!!.getString("trackname", "")
        artist = intent!!.getString("artist", "")
        duration = intent!!.getInt("duration", 0)
        playlistid = intent!!.getInt("playlistid", 0)

        trackListViewModel = ViewModelProvider(this).get(TrackListViewModel::class.java)

        if(trackname == "" || playlistname == ""){
            Toast.makeText(this, "Added Unsucessful!", Toast.LENGTH_SHORT).show()
        } else {
            trackListViewModel!!.insert(TrackList(playlistname, trackname, artist, duration, playlistid))
            Toast.makeText(this, playlistid.toString() + ": Added to " + playlistname + " Successful!" , Toast.LENGTH_SHORT).show()
        }

        val intent1 = Intent(this, MainpageActivity::class.java)
        startActivity(intent1)
    }
}


