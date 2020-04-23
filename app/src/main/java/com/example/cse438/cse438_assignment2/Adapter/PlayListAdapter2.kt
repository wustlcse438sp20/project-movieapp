package com.example.cse438.cse438_assignment2.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.cse438_assignment2.*
import com.example.cse438.cse438_assignment2.Database.PlayList
import com.example.cse438.cse438_assignment2.AddtoPlaylistActivity

class PlayListViewHolder2(inflater: LayoutInflater, parent: ViewGroup, moviename: String) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.playlist_layout2, parent, false)) {
    private val playListName: TextView

    init {
        playListName = itemView.findViewById(R.id.playList_name)
    }

    fun bind(playList: PlayList, moviename: String) {

        val context = itemView.getContext()
        playListName!!.text = playList.name
        itemView.setOnClickListener {
            val intent = Intent(context, AddtoPlaylistActivity::class.java)
            intent.putExtra("playlistname", playList.name)
            intent.putExtra("playlistid", playList.playlistid)
//            intent.putExtra("trackname", trackname)
//            intent.putExtra("artist", artist)
//            intent.putExtra("duration", duration)
            intent.putExtra("moviename", moviename)
            itemView.getContext().startActivity(intent)
        }
    }
}

class PlayListAdapter2(private val list: ArrayList<PlayList>?, private val moviename: String):
    RecyclerView.Adapter<PlayListViewHolder2>() {
    private var listPlayLists: ArrayList<PlayList>? = list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayListViewHolder2 {
        val inflater = LayoutInflater.from(parent.context)
        return PlayListViewHolder2(inflater, parent, moviename)
    }

    //bind the object
    override fun onBindViewHolder(holder: PlayListViewHolder2, position: Int) {
        val playList: PlayList = listPlayLists!!.get(position)
        holder.bind(playList, moviename)
    }

    //set the count
    override fun getItemCount(): Int = list!!.size

}