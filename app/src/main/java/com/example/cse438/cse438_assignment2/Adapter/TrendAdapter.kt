package com.example.cse438.cse438_assignment2.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.cse438_assignment2.Data.TrendingResult
import com.example.cse438.cse438_assignment2.R
import com.example.cse438.cse438_assignment2.ShowMovieActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.trend_layout.view.*

class TrendViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.trend_layout, parent, false)) {
    private var trendNameView: TextView
    private var trendid: Int
    private var url: String
    private var releaseDate: String
    private var title: String
    private var overview: String
    private var movieUrl: String




    private val trendImgView: ImageView

    init {
        trendNameView = itemView.findViewById(R.id.trendImgName)
        trendImgView = itemView.findViewById(R.id.trendImg)
        trendid = 0
        url = ""
        releaseDate=""
        title=""
        overview=""
        movieUrl = ""
    }

    fun bind(trendingResult: TrendingResult) {
        val context = itemView.getContext();
        trendNameView?.text = trendingResult.title
        trendid = trendingResult.id
        releaseDate=trendingResult.release_date
        title=trendingResult.title
        overview=trendingResult.overview
        url = "https://image.tmdb.org/t/p/w500" + trendingResult.poster_path
        movieUrl = trendingResult.backdrop_path;
        Picasso.get().load(url).into(trendImgView)
        itemView.trendImg.setOnClickListener {
            val intent = Intent(context, ShowMovieActivity::class.java)
            intent.putExtra("trendid", trendid)
            intent.putExtra("url", "https://image.tmdb.org/t/p/w500" + trendingResult.poster_path)
            intent.putExtra("title", title)
            intent.putExtra("overview", overview)
            intent.putExtra("releaseDate", releaseDate)
            itemView.getContext().startActivity(intent)
        }

        itemView.trendImgName.setOnClickListener {
            //Todo: Pop Up Window
        }
    }

}


//define the adapter for the recycler view
class TrendListAdapter(private val list: ArrayList<TrendingResult>) :
    RecyclerView.Adapter<TrendViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TrendViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: TrendViewHolder, position: Int) {
        val trendingResult: TrendingResult = list[position]
        holder.bind(trendingResult)
    }

    override fun getItemCount(): Int = list.size

}