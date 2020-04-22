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

    private val moviename: TextView


    init {
        moviename = itemView.findViewById(R.id.movie_name)

    }

    fun bind(track: TrackList) {
        val context = itemView.getContext();
        moviename!!.text = track.trackname

        itemView.setOnClickListener {
            val intent = Intent(context, DeleteTrackListActivity::class.java)
            intent.putExtra("tracklistid", track.tracklistid)
            intent.putExtra("playlistid", track.playlistid)
            intent.putExtra("moviename", track.trackname)
            intent.putExtra("playlistname", track.Playlistname)
            itemView.getContext().startActivity(intent)
        }

    }

}


class TrackInPlayListAdapter(private val list: ArrayList<TrackList>?) :
    RecyclerView.Adapter<TrackInPlayListAdapterViewHolder>() {
    private var showtrackLists: ArrayList<TrackList>? = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackInPlayListAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TrackInPlayListAdapterViewHolder(inflater, parent)

    }

    override fun onBindViewHolder(holder: TrackInPlayListAdapterViewHolder, position: Int) {
        val trackList: TrackList = showtrackLists!!.get(position)
        holder.bind(trackList)
    }

    override fun getItemCount(): Int = list!!.size

}