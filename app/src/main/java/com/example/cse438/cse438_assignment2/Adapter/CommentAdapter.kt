package com.example.cse438.cse438_assignment2.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.cse438_assignment2.AddtoPlaylistActivity
import com.example.cse438.cse438_assignment2.Database.PlayList
import com.example.cse438.cse438_assignment2.R
import com.example.cse438.cse438_assignment2.ShowPlaylistTracksActivity
import com.example.cse438.cse438_assignment2.UpdateplaylistActivity
import kotlinx.android.synthetic.main.comment_layout.view.*
import kotlinx.android.synthetic.main.playlist_layout.view.*

class CommentViewHolder(inflater: LayoutInflater, parent: ViewGroup, moviename: String) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.comment_layout, parent, false)) {
    private val commentDetail: TextView

    init {
        commentDetail = itemView.findViewById(R.id.comment_detail)
    }

    fun bind(comment: String, moviename: String) {

        commentDetail!!.text = comment

}

class CommentAdapter(private val list: ArrayList<String>?, private val moviename: String):
    RecyclerView.Adapter<CommentViewHolder>() {
    private var listComment: ArrayList<String>? = list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CommentViewHolder(inflater, parent, moviename)
    }

    //bind the object
    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment: String = listComment!!.get(position)
        holder.bind(comment, moviename)
    }

    //set the count
    override fun getItemCount(): Int = list!!.size

}}