package com.example.cse438.cse438_assignment2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.android.synthetic.main.activity_profile_pic.*

class AddProfilePicActivity : AppCompatActivity() {
    var profilePics: ArrayList<ImageView> = ArrayList<ImageView>()
    lateinit var db: FirebaseFirestore
    val documentReference by lazy {
        val db = FirebaseFirestore.getInstance()
        return@lazy db.document("players/${FirebaseAuth.getInstance()?.currentUser?.uid}")
    }
    lateinit var imgsrc:String
    //var profile1Src: String = image_profile1.src

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_pic)

        //Add all pics
        profilePics.add(image_profile1)
        profilePics.add(image_profile2)
        profilePics.add(image_profile3)
        profilePics.add(image_profile4)
        profilePics.add(image_profile5)
        profilePics.add(image_profile6)
        profilePics.add(image_profile7)
        profilePics.add(image_profile8)
    }

    override fun onStart() {
        super.onStart()
    }

    fun addProfilePic(view: View) {
        var src = view.getTag().toString()
        var resourceId:Int = this.getResources().
            getIdentifier(src, "drawable", this.getPackageName());
        imgsrc = src
        //Test_test.text = src

        db = FirebaseFirestore.getInstance()
        val settings = FirebaseFirestoreSettings.Builder()
            .build()
        db.setFirestoreSettings(settings)
        documentReference.update("wins",resourceId)

        Toast.makeText(this, "Profile Picture Changed", Toast.LENGTH_LONG).show()
        startActivity(Intent(this, MainpageActivity::class.java))
    }
}