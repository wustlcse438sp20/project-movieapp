
package com.example.cse438.cse438_assignment2.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
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
import java.util.*
import kotlin.collections.ArrayList


class TrackInPlayListAdapterViewHolder(movieItemView: View) : RecyclerView.ViewHolder(movieItemView) {

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


class TrackInPlayListAdapter(private val list: ArrayList<TrackList>?, private val draglistener: onDrag) :
    RecyclerView.Adapter<TrackInPlayListAdapterViewHolder>(), MoveItemListener.Listener {
    private var showtrackLists: ArrayList<TrackList>? = list


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackInPlayListAdapterViewHolder {
        val movieItemView = LayoutInflater.from(parent.context).inflate(R.layout.trackinplaylist_layout, parent, false)
        return TrackInPlayListAdapterViewHolder(movieItemView)

    }

    override fun onBindViewHolder(holder: TrackInPlayListAdapterViewHolder, position: Int) {
        val trackList: TrackList = showtrackLists!!.get(position)
        holder.bind(trackList)

        holder.itemView.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                this.draglistener.dragItem(holder)
            }
            return@setOnTouchListener true
        }
    }

    override fun getItemCount(): Int = list!!.size

    override fun moveRow(from: Int, to : Int) {
        if (from < to ) {
            for (i in from until to) {
                Collections.swap(showtrackLists, i, i + 1)
            }
        } else {
            for (i in from downTo to + 1) {
                Collections.swap(showtrackLists, i, i - 1)
            }
        }
        notifyItemMoved(from, to)
    }


    override fun clearRow(itemViewHolder: TrackInPlayListAdapterViewHolder) {}

    override fun selectRow(itemViewHolder: TrackInPlayListAdapterViewHolder) {}

}