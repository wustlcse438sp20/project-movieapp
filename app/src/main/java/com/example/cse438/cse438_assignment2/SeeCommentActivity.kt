package com.example.cse438.cse438_assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cse438.cse438_assignment2.Adapter.CommentViewHolder
import com.example.cse438.cse438_assignment2.Adapter.PlayListAdapter2
import com.example.cse438.cse438_assignment2.Database.PlayList
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.android.synthetic.main.activity_see_comment.*
import kotlinx.android.synthetic.main.activity_show_playlist.*

class SeeCommentActivity : AppCompatActivity() {
    private var  listComment: ArrayList<String> = ArrayList<String>()
    private var commentViewModel : CommentViewModel? = null

    lateinit var moviename: String

    override fun onCreate(savedInstanceState: Bundle?) {
        val intent = intent.extras
//        trackname = intent!!.getString("trackname", "")
//        artist = intent!!.getString("artist", "")
//        duration = intent!!.getInt("duration", 0)
        moviename = intent!!.getString("moviename","")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_comment)
    }

    override fun onStart() {
        super.onStart()

        //set recycler view
        val recyclerView = seeCommentRecyclerView
        val adapter = CommentViewHolder.CommentAdapter(listComment, moviename)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        commentViewModel = ViewModelProvider(this,CommentViewModelFactory(this.application,moviename)).get(CommentViewModel::class.java)
        commentViewModel!!.allComments.observe(this, Observer { returnTrack ->
            listComment.clear()
            listComment.addAll(returnTrack)
            Toast.makeText(this, "There are " + listComment.size.toString() + " comments in this watchlist.", Toast.LENGTH_SHORT).show()
            adapter.notifyDataSetChanged()
        }



        )

    }

    fun backtohome(view: View){
        val intent = Intent(this, MainpageActivity::class.java)
        startActivity(intent)
    }




}
