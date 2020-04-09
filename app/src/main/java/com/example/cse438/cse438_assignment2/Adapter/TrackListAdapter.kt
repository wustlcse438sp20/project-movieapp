package com.example.cse438.cse438_assignment2.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.cse438_assignment2.Data.Track
import com.example.cse438.cse438_assignment2.R
import com.example.cse438.cse438_assignment2.ShowTrackActivity
import com.squareup.picasso.Picasso
import retrofit2.http.Url
import java.net.URL
import java.nio.charset.Charset

class TrackViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.track_layout, parent, false)) {
    private var trackNameView: TextView
    private var trackid: Int
    private var url: String



    private val trackImgView: ImageView

    init {
        trackNameView = itemView.findViewById(R.id.trackImgName)
        trackImgView = itemView.findViewById(R.id.trackImg)
        trackid = 0
        url = ""
    }

    fun bind(track: Track) {
        val context = itemView.getContext();
        trackNameView?.text = track.title
        trackid = track.id
        url = track.link
        Picasso.get().load(track.album.cover_medium).into(trackImgView)
        itemView.setOnClickListener {
            val intent = Intent(context, ShowTrackActivity::class.java)
            intent.putExtra("trackid", trackid)
            intent.putExtra("url", url)
            intent.putExtra("title", track.title)
            intent.putExtra("rank", track.rank)
            intent.putExtra("album", track.album.title)
            intent.putExtra("duration", track.duration)
            intent.putExtra("artist", track.artist.name)
            intent.putExtra("image", track.album.cover_medium)
            intent.putExtra("position", track.position)
            intent.putExtra("artistImage", track.artist.picture_medium)
            itemView.getContext().startActivity(intent)


        }
    }

}


//define the adapter for the recycler view
class TrackListAdapter(private val list: ArrayList<Track>) :
    RecyclerView.Adapter<TrackViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TrackViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        val track: Track = list[position]
        holder.bind(track)
    }

    override fun getItemCount(): Int = list.size

}