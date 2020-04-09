package com.example.cse438.cse438_assignment2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cse438.cse438_assignment2.Adapter.TrackInPlayListAdapter
import com.example.cse438.cse438_assignment2.Database.TrackList
import kotlinx.android.synthetic.main.activity_show_playlist.*

class DeleteTrackListActivity : AppCompatActivity() {
    private var playlistid: Int = 0
    private var tracklistid: Int = 0
    private var trackname: String = ""
    private var playlistname: String = ""
    var playlistGenre: String? = ""
    var rating: Int? = 0
    private var returnTracks: ArrayList<TrackList> = ArrayList()

    private var trackListViewModel : TrackListViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val intent = intent.extras
        playlistid = intent!!.getInt("playlistid", 0)
        tracklistid = intent!!.getInt("tracklistid", 0)
        trackname = intent!!.getString("trackname", "")
        playlistGenre = intent!!.getString("genre", "")
        rating = intent!!.getInt("rating", 0)
        playlistname = intent!!.getString("playlistname", "")

        trackListViewModel = ViewModelProvider(this).get(TrackListViewModel::class.java)

        if (tracklistid != 0 && playlistid != 0) {
            trackListViewModel!!.deleteplaylist(playlistid, tracklistid)
            Toast.makeText(this, "Delete " + trackname + " from " + playlistname + " Successful!" , Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Delete unsuccessfully!", Toast.LENGTH_SHORT).show()
        }

        val intent1 = Intent(this, MainActivity::class.java)
        startActivity(intent1)

    }


}