package com.example.cse438.cse438_assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.android.synthetic.main.activity_updateplaylist.*

class UpdateplaylistActivity : AppCompatActivity() {

    private var name: String = ""
    private var genre: String = ""
    private var description: String = ""
    private var rating: Int = 0
    private var id: Int = 0
    private var playListViewModel : PlayListViewModel4? = null

    lateinit var username: String
    lateinit var db: FirebaseFirestore
    val documentReference by lazy {
        val db = FirebaseFirestore.getInstance()
        return@lazy db.document("players/${FirebaseAuth.getInstance()?.currentUser?.uid}")
    }
    lateinit var email:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val intent = intent.extras
        name = intent!!.getString("name", "")
        genre = intent!!.getString("genre", "")
        description = intent!!.getString("description", "")
        rating = intent!!.getInt("rating", 0)
        id = intent!!.getInt("id", 0)

        setContentView(R.layout.activity_updateplaylist)

        updateplaylistname.hint = name
        updateplaylistdescription.hint = description
        updateplaylistgenre.hint = genre
        updateplaylistrating.hint = rating.toString()



    }


    override fun onStart() {
        super.onStart()


        db = FirebaseFirestore.getInstance()
        val settings = FirebaseFirestoreSettings.Builder()
            .setTimestampsInSnapshotsEnabled(true)
            .build()
        db.setFirestoreSettings(settings)
        documentReference.get().addOnSuccessListener {

            username=it.get("username", String::class.java)!!
            email=it.get("email", String::class.java)!!

            //set view model
            playListViewModel = ViewModelProvider(this,PlayListViewModelFactory4(this!!.application,email)).get(PlayListViewModel4::class.java)

        }

        submitupdateplaylist.setOnClickListener {
            name = updateplaylistname.text.toString()
            description = updateplaylistdescription.text.toString()
            genre = updateplaylistgenre.text.toString()
            rating = 0
            rating = updateplaylistrating.text.toString().toInt()

            if (name == "" || description == "" || genre == "" || rating == 0) {
                Toast.makeText(this, "Watchlist updated Unsucessful! Please enter valid value (rating need to be positive) for all fields.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "called2", Toast.LENGTH_SHORT).show()
                playListViewModel!!.update(name, description, genre, rating, id)
                Toast.makeText(this, "Update " + name + " successfully!", Toast.LENGTH_SHORT).show()
            }

            val intent1 = Intent(this, MainpageActivity::class.java)
            startActivity(intent1)
        }



    }



}
