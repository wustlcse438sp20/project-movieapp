package com.example.cse438.cse438_assignment2

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModelProvider
import com.example.cse438.cse438_assignment2.Data.Chart
import com.example.cse438.cse438_assignment2.Data.Track
import com.example.cse438.cse438_assignment2.Database.*
import com.example.cse438_rest_studio.TrackViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_show_track.*
import kotlinx.android.synthetic.main.add_new_playlist.*
import kotlinx.android.synthetic.main.add_new_playlist.view.*
import kotlinx.android.synthetic.main.add_track_to_playlist.*
import kotlinx.android.synthetic.main.add_track_to_playlist.view.*
import retrofit2.Response
import retrofit2.http.GET
import java.io.ByteArrayInputStream
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset
import java.util.zip.GZIPInputStream


class ShowTrackActivity : AppCompatActivity() {
    var trackid: Int = 0
    var url: String? = ""
    var duration: Int = 0
    var rank: Int = 0
    var title: String? = ""
    var artist: String? = ""
    var image: String? = ""
    var position = 0
    var album: String? = ""
    var artistImage: String?=""
    private var  listplayList: ArrayList<PlayList> = ArrayList<PlayList>()
    private var playListViewModel : PlayListViewModel2? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_track)
    }

    override fun onStart() {
        super.onStart()

        //store the intent value
        var bundle: Bundle? = intent.extras
        trackid = bundle!!.getInt("trackid")
        rank = bundle.getInt("rank")
        duration = bundle!!.getInt("duration")
        title = bundle!!.getString("title")
        url = bundle!!.getString("url")
        artist = bundle!!.getString("artist")
        image = bundle!!.getString("image")
        position = bundle!!.getInt("position")
        album = bundle!!.getString("album")
        artistImage=bundle!!.getString("artistImage")
        trackDetailName.text = title
        Picasso.get().load(image).into(trackDetailImg)
        trackDetailArtist.text = "Artist: " + artist
        trackDetailLength.text = "Length: " + duration.toString()
        trackDetailRank.text = "Rank: " + rank.toString()
        trackDetailPosition.text = "Position: " + position.toString()
        trackDetailTrackName.text = "Album: " + album

        playListViewModel = ViewModelProvider(this).get(PlayListViewModel2::class.java)

        //observe the allEvents LiveData
        playListViewModel!!.allPlaylists.observe(this, Observer { playlists ->
            // Update the cached copy of the words in the adapter.
            listplayList.clear()
            listplayList.addAll(playlists)
        })

    }

    fun back(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun addToPlayList(view: View) {
        if (listplayList.size != 0) {
            val intent = Intent(this, ChoosePlaylistActivity::class.java)
            intent.putExtra("trackname", title)
            intent.putExtra("artist", artist)
            intent.putExtra("duration", duration)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Please create playlist first!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    fun showArtist(view: View){





        val intent = Intent(this, ShowArtistActivity::class.java)
        intent.putExtra("artist", artist)
        intent.putExtra("artistImage", artistImage)
        startActivity(intent)
    }




}







