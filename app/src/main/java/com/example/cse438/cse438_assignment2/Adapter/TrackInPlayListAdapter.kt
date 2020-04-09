package com.example.cse438.cse438_assignment2.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.cse438_assignment2.Data.Track
import com.example.cse438.cse438_assignment2.Database.TrackList
import com.example.cse438.cse438_assignment2.DeleteTrackListActivity
import com.example.cse438.cse438_assignment2.R
import com.squareup.picasso.Picasso

class TrackInPlayListAdapterViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.trackinplaylist_layout, parent, false)) {

    private val trackListName: TextView
    private val trackListArtist: TextView
    private val trackListDuration: TextView
    private val playlistGenre: TextView
    private val playlistRating: TextView


    init {
        trackListName = itemView.findViewById(R.id.track_name)
        trackListArtist = itemView.findViewById(R.id.track_artist)
        trackListDuration = itemView.findViewById(R.id.track_time)
        playlistGenre = itemView.findViewById(R.id.genre)
        playlistRating = itemView.findViewById(R.id.playlistRating)

    }

    fun bind(track: TrackList, genre: String, rating: Int) {
        val context = itemView.getContext();
        trackListName!!.text = track.trackname
        trackListArtist!!.text = track.artist
        trackListDuration!!.text = track.duration.toString()
        playlistGenre!!.text = genre
        playlistRating!!.text = rating.toString()

        itemView.setOnClickListener {
            val intent = Intent(context, DeleteTrackListActivity::class.java)
            intent.putExtra("tracklistid", track.tracklistid)
            intent.putExtra("playlistid", track.playlistid)
            intent.putExtra("trackname", track.trackname)
            intent.putExtra("genre", genre)
            intent.putExtra("rating", rating)
            intent.putExtra("playlistname", track.Playlistname)
            itemView.getContext().startActivity(intent)
        }

    }

}


class TrackInPlayListAdapter(private val list: ArrayList<TrackList>?, private val genre: String, private val rating: Int) :
    RecyclerView.Adapter<TrackInPlayListAdapterViewHolder>() {
    private var showtrackLists: ArrayList<TrackList>? = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackInPlayListAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TrackInPlayListAdapterViewHolder(inflater, parent)

    }

    override fun onBindViewHolder(holder: TrackInPlayListAdapterViewHolder, position: Int) {
        val trackList: TrackList = showtrackLists!!.get(position)
        holder.bind(trackList, genre, rating)
    }

    override fun getItemCount(): Int = list!!.size

}