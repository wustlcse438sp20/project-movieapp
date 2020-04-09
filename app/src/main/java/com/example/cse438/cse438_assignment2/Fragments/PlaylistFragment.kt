package com.example.cse438.cse438_assignment2.Fragments

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cse438.cse438_assignment2.Adapter.PlayListAdapter
import com.example.cse438.cse438_assignment2.Data.Track
import com.example.cse438.cse438_assignment2.Database.PlayList
import com.example.cse438.cse438_assignment2.PlayListViewModel

import com.example.cse438.cse438_assignment2.R
import kotlinx.android.synthetic.main.add_new_playlist.*
import kotlinx.android.synthetic.main.add_new_playlist.view.*
import kotlinx.android.synthetic.main.fragment_playlist.*


class PlaylistFragment : Fragment() {
    private var  listplayList: ArrayList<PlayList> = ArrayList<PlayList>()
    private var playListViewModel : PlayListViewModel? = null
    public lateinit  var addButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_playlist, container, false)
    }

    override fun onStart() {
        super.onStart()

        //set recycler view
        val recyclerView = playlist_recycler_view
        val adapter = PlayListAdapter(listplayList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        //set view model
        playListViewModel = ViewModelProvider(this).get(PlayListViewModel::class.java)

        //observe the allEvents LiveData
        playListViewModel!!.allPlaylists.observe(this, Observer { playlists ->
            // Update the cached copy of the words in the adapter.
            listplayList.clear()
            listplayList.addAll(playlists)
            adapter.notifyDataSetChanged()
        })
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //set views
        addButton = add_button
        addButton.setOnClickListener{
            dialogView()
        }

    }

    private fun dialogView() {
        val dialogView = LayoutInflater.from(this.activity).inflate(R.layout.add_new_playlist, null)
        val mBuilder = AlertDialog.Builder(this.activity)
            .setView(dialogView)
            .setTitle("Add New Playlist")
        val mAlertDialog = mBuilder.show()

        mAlertDialog.submitaddplaylist.setOnClickListener {
            var name = dialogView.inputplaylistname.text.toString()
            var description = dialogView.inputplaylistdescription.text.toString()
            var genre = dialogView.inputplaylistgenre.text.toString()
            var rating = 0
            rating = dialogView.inputplaylistrating.text.toString().toInt()

            if(name == "" || description == "" || genre == "" || rating == 0 ){
                Toast.makeText(this.context, "Playlist created Unsucessful! Please enter valid value for all fields.", Toast.LENGTH_SHORT).show()
            } else {
                mAlertDialog.dismiss()
                playListViewModel!!.insert(PlayList(name, description, rating, genre))
                Toast.makeText(this.context, "Playlist created!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}