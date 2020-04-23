package com.example.cse438.cse438_assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_updateplaylist.*

class UpdateplaylistActivity : AppCompatActivity() {

    private var name: String = ""
    private var genre: String = ""
    private var description: String = ""
    private var rating: Int = 0
    private var id: Int = 0
    private var playListViewModel : PlayListViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val intent = intent.extras
        name = intent!!.getString("name", "")
        genre = intent!!.getString("genre", "")
        description = intent!!.getString("description", "")
        rating = intent!!.getInt("rating", 0)
        id = intent!!.getInt("id", 0)

        updateplaylistname.setText(name)
        updateplaylistdescription.setText(description)
        updateplaylistgenre.setText(genre)
        updateplaylistrating.setText(rating)

        setContentView(R.layout.activity_updateplaylist)
    }


    override fun onStart() {
        super.onStart()

        submitupdateplaylist.setOnClickListener {
            name = updateplaylistname.text.toString()
            description = updateplaylistdescription.text.toString()
            genre = updateplaylistgenre.text.toString()
            rating = 0
            rating = updateplaylistrating.text.toString().toInt()

            if (name == "" || description == "" || genre == "" || rating == 0) {
                Toast.makeText(this, "Watchlist updated Unsucessful! Please enter valid value (rating need to be positive) for all fields.", Toast.LENGTH_SHORT).show()
            } else {
                playListViewModel!!.update(name, description, genre, rating, id)
                Toast.makeText(this, "Update " + name + " successfully!", Toast.LENGTH_SHORT).show()
            }
        }

    }


}
