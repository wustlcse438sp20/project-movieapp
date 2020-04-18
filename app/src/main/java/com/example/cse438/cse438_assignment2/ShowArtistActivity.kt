package com.example.cse438.cse438_assignment2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cse438.cse438_assignment2.Database.PlayList
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_show_artist.*
import kotlinx.android.synthetic.main.activity_show_movie.*
import kotlinx.android.synthetic.main.content_show_artist.*

class ShowArtistActivity : AppCompatActivity() {


    var artist: String? = ""

    var artistImage: String?=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_artist)
    }

    override fun onStart() {
        super.onStart()

        //store the intent value
        var bundle: Bundle? = intent.extras

        artist = bundle!!.getString("artist")
        artistImage=bundle!!.getString("artistImage")
        println(artistImage)
        Picasso.get().load(artistImage).into(artistDetailImg)
        artistDetailName.text = artist
        println("name is :" + artist)




    }

    fun backToHome(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }



}
