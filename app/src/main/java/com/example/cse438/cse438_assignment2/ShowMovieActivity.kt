package com.example.cse438.cse438_assignment2

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import android.widget.VideoView
import androidx.annotation.NonNull
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModelProvider
import com.example.cse438.cse438_assignment2.Data.Chart
import com.example.cse438.cse438_assignment2.Data.Track
import com.example.cse438.cse438_assignment2.Database.*
import com.example.cse438_rest_studio.TrackViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_show_movie.*
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


class ShowMovieActivity : AppCompatActivity() {
    private var trendid: Int? = 0
    private var url: String? = ""
    private var releaseDate: String? = ""
    private var title: String? = ""
    private var overview: String? = ""

    //    var id: Int = 0
//    var url: String? = ""
//    var duration: Int = 0
//    var rank: Int = 0
//    var title: String? = ""
//    var artist: String? = ""
//    var image: String? = ""
//    var position = 0
//    var album: String? = ""
//    var artistImage: String?=""
    private var listplayList: ArrayList<PlayList> = ArrayList<PlayList>()
    private var playListViewModel: PlayListViewModel2? = null
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
//        trackDetailPosition.text = "Position: " + position.toString()
//        trackDetailTrackName.text = "Album: " + album

        //play video
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
        playListViewModel = ViewModelProvider(this).get(PlayListViewModel2::class.java)

        //observe the allEvents LiveData
        playListViewModel!!.allPlaylists.observe(this, Observer { playlists ->
            // Update the cached copy of the words in the adapter.
            listplayList.clear()
            listplayList.addAll(playlists)
        })

    }

    fun back(view: View) {
        val intent = Intent(this, MainpageActivity::class.java)
        startActivity(intent)
    }

    fun addToPlayList(view: View) {
        if (listplayList.size != 0) {
            val intent = Intent(this, ChoosePlaylistActivity::class.java)
            intent.putExtra("moviename", title)
//            intent.putExtra("overview", overview)
//            intent.putExtra("duration", duration)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Please create watchlist first!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainpageActivity::class.java)
            startActivity(intent)
        }
    }


}







