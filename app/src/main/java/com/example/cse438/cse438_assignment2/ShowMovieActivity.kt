package com.example.cse438.cse438_assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cse438.cse438_assignment2.Database.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_show_movie.*

class ShowMovieActivity : AppCompatActivity() {
    private var trendid: Int? = 0
    private var url: String? = ""
    private var releaseDate: String? = ""
    private var title: String? = ""
    private var overview: String? = ""

    private var listplayList: ArrayList<PlayList> = ArrayList<PlayList>()
    private var playListViewModel : PlayListViewModel3? = null
    lateinit var name: String
    lateinit var db: FirebaseFirestore
    val documentReference by lazy {
        val db = FirebaseFirestore.getInstance()
        return@lazy db.document("players/${FirebaseAuth.getInstance()?.currentUser?.uid}")
    }
    lateinit var email:String
    //private var moviePreview: YouTubePlayerView = movie_preview;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_movie)
    }

    override fun onStart() {
        super.onStart()

        //store the intent value
        var bundle: Bundle? = intent.extras
        trendid = bundle!!.getInt("trendid")
        overview = bundle!!.getString("overview")

        url = bundle!!.getString("url")
        releaseDate = bundle!!.getString("releaseDate")
        title = bundle!!.getString("title")
        url = bundle!!.getString("url")
        movieDetailName.text = title
        Picasso.get().load(url).into(movieDetailImg)
        movieDetailName.text = "Title: " + title
        movieDetailReleaseDate.text = "Release Date: " + releaseDate
        movieDetailOverview.text = "Overview: " + overview

        movie_preview.getPlayerUiController().showFullscreenButton(true)
        movie_preview.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                var videoId = "jCFWEzIVILc"
                if(trendid == 38700){
                    videoId = "jKCj3XuPG8M"
                } else if (trendid == 181812) {
                    videoId = "adzYW5DZoWs"
                } else if (trendid == 443791) {
                    videoId ="jCFWEzIVILc"
                } else if (trendid == 454626) {
                    videoId = "szby7ZHLnkA"
                } else if (trendid == 495764) {
                    videoId ="ptLZlrE8MrQ"
                } else if (trendid == 446893) {
                    videoId = "SyTg7RIn-X8"
                } else if (trendid == 338762) {
                    videoId = "F95Fk255I4M"
                }

                youTubePlayer.cueVideo(videoId, 0f)
            }
        })
        db = FirebaseFirestore.getInstance()
        val settings = FirebaseFirestoreSettings.Builder()
            .setTimestampsInSnapshotsEnabled(true)
            .build()
        db.setFirestoreSettings(settings)
        documentReference.get().addOnSuccessListener {

            name=it.get("username", String::class.java)!!
            email=it.get("email", String::class.java)!!

            //set view model
            playListViewModel = ViewModelProvider(this,PlayListViewModelFactory3(this!!.application,email)).get(PlayListViewModel3::class.java)

            //observe the allEvents LiveData
            playListViewModel!!.allPlaylists.observe(this, Observer { playlists ->
                // Update the cached copy of the words in the adapter.
                listplayList.clear()
                listplayList.addAll(playlists)
            })
        }

    }

    fun back(view: View) {
        val intent = Intent(this, MainpageActivity::class.java)
        startActivity(intent)
    }

    fun addToPlayList(view: View) {
        if (listplayList.size != 0) {
            val intent = Intent(this, ChoosePlaylistActivity::class.java)
            intent.putExtra("moviename", title)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Please create watchlist first!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainpageActivity::class.java)
            startActivity(intent)
        }
    }


}







