package com.example.cse438.cse438_assignment2

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.ClipData
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.DragEvent
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.core.view.GestureDetectorCompat
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
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.LinearLayout


class ShowMovieActivity : AppCompatActivity() {
    private var trendid: Int? = 0
    private var url: String? = ""
    private var releaseDate: String? = ""
    private var title: String? = ""
    private var overview: String? = ""
//    private var height: Int = 0
//    private var width: Int = 0

    private var listplayList: ArrayList<PlayList> = ArrayList<PlayList>()
    private var playListViewModel : PlayListViewModel3? = null
    lateinit var name: String
    lateinit var db: FirebaseFirestore
    private lateinit var mDetector: GestureDetectorCompat
    private lateinit var posterView: View

    val documentReference by lazy {
        val db = FirebaseFirestore.getInstance()
        return@lazy db.document("players/${FirebaseAuth.getInstance()?.currentUser?.uid}")
    }
    lateinit var email:String
    //private var moviePreview: YouTubePlayerView = movie_preview;

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_movie)
        posterView = movieDetailImg
        mDetector = GestureDetectorCompat(this, MyGestureListener())

        posterView.setOnLongClickListener { v: View ->

            val data = ClipData.newPlainText("", "");

            v.startDragAndDrop(
                data,
                View.DragShadowBuilder(v),
                v,
                0
            )
            true
        }
//        var mdl = MyDragListener()
//        posterView.setOnDragListener(mdl)
//        showmovieLayout.setOnDragListener(mdl)
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

//        movie_preview.getPlayerUiController().showFullscreenButton(true)
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

    fun moveTo(targetX: Float, targetY: Float) {

        val animSetXY = AnimatorSet()

        val x = ObjectAnimator.ofFloat(
            posterView,
            "translationX",
            posterView.translationX,
            targetX
        )

        val y = ObjectAnimator.ofFloat(
            posterView,
            "translationY",
            posterView.translationY,
            targetY
        )

        animSetXY.playTogether(x, y)
        animSetXY.duration = 300
        animSetXY.start()

    }


    private inner class MyGestureListener : GestureDetector.SimpleOnGestureListener() {

        private var swipedistance = 50

        override fun onDoubleTap(event: MotionEvent?): Boolean {
            if (!movie_preview.isFullScreen()) {
                movie_preview.enterFullScreen()
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
                // Hide ActionBar
                if (supportActionBar != null) {
                    supportActionBar!!.hide()
                }
            }
            return true
        }

        override fun onFling(
            e1: MotionEvent,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            if (e1.y - e2.y > swipedistance) { //swipe up
                moveTo(0f, -500f)
                return true
            }
            return false
        }

        override fun onLongPress(event: MotionEvent) {
            movieDetailImg.getLayoutParams().height = 1000
            movieDetailImg.getLayoutParams().width = 1000
            movieDetailImg.requestLayout()
        }

        override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
            movieDetailImg.layoutParams.width = 450
            movieDetailImg.layoutParams.height = 450
            moveTo(0f, 0f)
            movieDetailImg.requestLayout()
            return true
        }



    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        mDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

//    private inner class MyDragListener : View.OnDragListener {
//
//        private lateinit var img: View
//        override fun onDrag(v: View, event: DragEvent): Boolean {
//            val action = event.action
//            when(action) {
//                DragEvent.ACTION_DRAG_STARTED -> {
//                    if(event.localState != null) {
//                        img = event.localState as View
//                    }
//                }
//                DragEvent.ACTION_DRAG_ENTERED -> {}
//                DragEvent.ACTION_DRAG_EXITED -> {}
//                DragEvent.ACTION_DROP -> {
//                    if(img != null) {
//                        img.x = event.x - img.width / 2
//                        img.y = event.y - img.height / 2
//                    }
//                }
//                DragEvent.ACTION_DRAG_ENDED -> {}
//                else -> {}
//
//            }
//            return true
//        }
//    }


}







