package com.example.cse438.cse438_assignment2.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.cse438_assignment2.Database.PlayList
import com.example.cse438.cse438_assignment2.R
import com.example.cse438.cse438_assignment2.ShowPlaylistTracksActivity
import com.example.cse438.cse438_assignment2.UpdateplaylistActivity
import kotlinx.android.synthetic.main.playlist_layout.view.*


//create the view holder
class PlayListViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.playlist_layout, parent, false)) {
    private val playListName: TextView
    private val playListDescription: TextView
    private val playListGenre: TextView
    private val playListRating: TextView

    init {
        playListName = itemView.findViewById(R.id.playList_name)
        playListDescription = itemView.findViewById(R.id.playList_description)
        playListGenre = itemView.findViewById(R.id.playlist_genre)
        playListRating = itemView.findViewById(R.id.playList_rating)

//        name=itemView.findViewById(R.id.inputplaylistname).toString
    }

    fun bind(playList: PlayList) {
        val context = itemView.getContext();
        var playlistId = playList.playlistid
        playListName!!.text = playList.name
        playListDescription!!.text = playList.description
        playListGenre!!.text = playList.genre
        playListRating!!.text = playList.rating.toString()

        itemView.playList_name.setOnClickListener {
            val intent1 = Intent(context, ShowPlaylistTracksActivity::class.java)
            intent1.putExtra("name", playList.name)
            intent1.putExtra("genre", playList.genre)
            intent1.putExtra("rating", playList.rating)
            intent1.putExtra("id", playlistId)
            itemView.getContext().startActivity(intent1)
        }

        itemView.playList_description.setOnClickListener {
            val intent1 = Intent(context, ShowPlaylistTracksActivity::class.java)
            intent1.putExtra("name", playList.name)
            intent1.putExtra("genre", playList.genre)
            intent1.putExtra("rating", playList.rating)
            intent1.putExtra("id", playlistId)
            itemView.getContext().startActivity(intent1)
        }

        itemView.playlist_genre.setOnClickListener {
            val intent1 = Intent(context, ShowPlaylistTracksActivity::class.java)
            intent1.putExtra("name", playList.name)
            intent1.putExtra("genre", playList.genre)
            intent1.putExtra("rating", playList.rating)
            intent1.putExtra("id", playlistId)
            itemView.getContext().startActivity(intent1)
        }

        itemView.playList_rating.setOnClickListener {
            val intent1 = Intent(context, ShowPlaylistTracksActivity::class.java)
            intent1.putExtra("name", playList.name)
            intent1.putExtra("genre", playList.genre)
            intent1.putExtra("rating", playList.rating)
            intent1.putExtra("id", playlistId)
            itemView.getContext().startActivity(intent1)
        }

        itemView.editImg.setOnClickListener {
            val intent1 = Intent(context, UpdateplaylistActivity::class.java)
            intent1.putExtra("name", playList.name)
            intent1.putExtra("genre", playList.genre)
            intent1.putExtra("rating", playList.rating)
            intent1.putExtra("id", playlistId)
            intent1.putExtra("description", playList.description)
            itemView.getContext().startActivity(intent1)
        }
    }
}

class PlayListAdapter(private val list: ArrayList<PlayList>?) :
    RecyclerView.Adapter<PlayListViewHolder>() {
    private var listPlayLists: ArrayList<PlayList>? = list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PlayListViewHolder(inflater, parent)
    }

    //bind the object
    override fun onBindViewHolder(holder: PlayListViewHolder, position: Int) {
        val playList: PlayList = listPlayLists!!.get(position)
        holder.bind(playList)
    }

    //set the count
    override fun getItemCount(): Int = list!!.size

}