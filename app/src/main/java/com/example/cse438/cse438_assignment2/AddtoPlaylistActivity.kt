package com.example.cse438.cse438_assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.cse438.cse438_assignment2.Database.TrackList
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.android.synthetic.main.activity_addto_playlist.*

class AddtoPlaylistActivity : AppCompatActivity() {
    private var moviename: String = ""
    private var playlistid: Int = 0
    private var playlistname: String = ""
    private var trackListViewModel: TrackListViewModel? = null
    private var name: String=""
    lateinit var db: FirebaseFirestore
    val documentReference by lazy {
        val db = FirebaseFirestore.getInstance()
        return@lazy db.document("players/${FirebaseAuth.getInstance()?.currentUser?.uid}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addto_playlist)
    }

    override fun onStart() {
        super.onStart()
        submitbutton.setOnClickListener {
            val intent = intent.extras
            playlistname = intent!!.getString("playlistname", "")
            moviename = intent!!.getString("moviename", "")
            playlistid = intent!!.getInt("playlistid", 0)

            trackListViewModel = ViewModelProvider(this).get(TrackListViewModel::class.java)

            if (moviename == "" || playlistname == "") {
                Toast.makeText(this, "Added Unsucessful!", Toast.LENGTH_SHORT).show()
            } else {

                val movierate = 0
                var comment = movie_comment.text.toString()

                if (movierate < 0 || movierate > 10 || comment == "") {
                    Toast.makeText(this, "Input invalid", Toast.LENGTH_SHORT).show()

                } else {

                    val email=FirebaseAuth.getInstance()?.currentUser?.email.toString()
                    name = email.substring(0,email.indexOf('@'))
                    comment=name+" : "+comment

                    trackListViewModel!!.insert(TrackList(playlistname, moviename, playlistid, movierate, comment))
                    Toast.makeText(
                        this,
                        playlistid.toString() + ": Added to " + playlistname + " Successful!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            val intent1 = Intent(this, MainpageActivity::class.java)
            startActivity(intent1)


        }
    }


}
