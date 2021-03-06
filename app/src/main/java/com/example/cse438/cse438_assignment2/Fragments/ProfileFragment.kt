package com.example.cse438.cse438_assignment2.Fragments

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cse438.cse438_assignment2.*
import com.example.cse438.cse438_assignment2.Adapter.PlayListAdapter
import com.example.cse438.cse438_assignment2.Data.Track
import com.example.cse438.cse438_assignment2.Database.PlayList

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.add_new_playlist.*
import kotlinx.android.synthetic.main.add_new_playlist.view.*
import kotlinx.android.synthetic.main.fragment_playlist.*
import kotlinx.android.synthetic.main.fragment_profile.*
import java.io.File


class ProfileFragment : Fragment() {
    lateinit var name: String
    lateinit var db: FirebaseFirestore
        val documentReference by lazy {
        val db = FirebaseFirestore.getInstance()
        return@lazy db.document("players/${FirebaseAuth.getInstance()?.currentUser?.uid}")
    }
    lateinit var email :String
    var imgsrc:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onStart() {
        super.onStart()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = FirebaseFirestore.getInstance()
        val settings = FirebaseFirestoreSettings.Builder()
            .setTimestampsInSnapshotsEnabled(true)
            .build()
        db.setFirestoreSettings(settings)
        documentReference.get().addOnSuccessListener {

            name=it.get("username", String::class.java)!!
            email=it.get("email", String::class.java)!!
            imgsrc=it.get("wins",Int::class.java)!!
            username.text=name
            profileEmail.text=email

            profileImg.setImageResource(imgsrc);
        }

        signout.setOnClickListener{
            var mAuth = FirebaseAuth.getInstance()
            mAuth.signOut()
            val intent = Intent(this.context, MainActivity::class.java)
            startActivity(intent)
        }

        changepassword.setOnClickListener{
            startActivity(Intent(activity, PasswordActivity::class.java))

        }

        changeprofilepic.setOnClickListener({
            startActivity(Intent(activity, AddProfilePicActivity::class.java))
        })



    }
}