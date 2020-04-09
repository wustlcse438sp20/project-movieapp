package com.example.cse438.cse438_assignment2.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.cse438_assignment2.*
import com.example.cse438.cse438_assignment2.Database.PlayList
import com.example.cse438.cse438_assignment2.Database.TrackList

class PlayListViewHolder2(inflater: LayoutInflater, parent: ViewGroup, trackname: String, artist: String, duration: Int) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.playlist_layout2, parent, false)) {
    private val playListName: TextView

    init {
        playListName = itemView.findViewById(R.id.playList_name)
    }

    fun bind(playList: PlayList, trackname: String, artist: String, duration: Int) {

        val context = itemView.getContext()
        playListName!!.text = playList.name
        itemView.setOnClickListener {
            val intent = Intent(context, AddtoPlaylistActivity::class.java)
            intent.putExtra("playlistname", playList.name)
            intent.putExtra("playlistid", playList.playlistid)
            intent.putExtra("trackname", trackname)
            intent.putExtra("artist", artist)
            intent.putExtra("duration", duration)
            itemView.getContext().startActivity(intent)
        }
    }
}

class PlayListAdapter2(private val list: ArrayList<PlayList>?, private val trackname: String, private val artist: String, private val duration: Int):
    RecyclerView.Adapter<PlayListViewHolder2>() {
    private var listPlayLists: ArrayList<PlayList>? = list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayListViewHolder2 {
        val inflater = LayoutInflater.from(parent.context)
        return PlayListViewHolder2(inflater, parent, trackname, artist, duration)
    }

    //bind the object
    override fun onBindViewHolder(holder: PlayListViewHolder2, position: Int) {
        val playList: PlayList = listPlayLists!!.get(position)
        holder.bind(playList, trackname, artist, duration)
    }

    //set the count
    override fun getItemCount(): Int = list!!.size

}