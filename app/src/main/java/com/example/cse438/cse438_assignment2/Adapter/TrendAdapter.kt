package com.example.cse438.cse438_assignment2.Adapter

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.content.Intent
import android.os.Build
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.cse438_assignment2.Data.TrendingResult
import com.example.cse438.cse438_assignment2.R
import com.example.cse438.cse438_assignment2.ShowMovieActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.trend_layout.view.*
import kotlinx.android.synthetic.main.popup_window.view.*

class TrendViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.trend_layout, parent, false)) {
    private var trendNameView: TextView
    private var trendid: Int
    private var url: String
    private var releaseDate: String
    private var title: String
    private var overview: String
    private var movieUrl: String
    private var myInflater: LayoutInflater = inflater
    private val trendImgView: ImageView
    private var myParent: ViewGroup = parent
    //private var popupOverview: TextView

    init {
        trendNameView = itemView.findViewById(R.id.trendImgName)
        //popupOverview = itemView.findViewById(R.id.popup_overview)
        trendImgView = itemView.findViewById(R.id.trendImg)
        trendid = 0
        url = ""
        releaseDate=""
        title=""
        overview=""
        movieUrl = ""
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
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

            val view = LayoutInflater.from(itemView.context).inflate(R.layout.popup_window, null)
            val popupWindow = PopupWindow(
                view, // Custom view to show in popup window
                LinearLayout.LayoutParams.WRAP_CONTENT, // Width of popup window
                LinearLayout.LayoutParams.WRAP_CONTENT // Window height
            )
            // Finally, show the popup window on app
            TransitionManager.beginDelayedTransition(myParent)
            view.popup_overview.text = "Overview: " + overview;
            view.popup_back.setOnClickListener(){
                popupWindow.dismiss()
            }
            popupWindow.setOnDismissListener {
                Toast.makeText(myParent.context,"Popup closed",Toast.LENGTH_SHORT).show()
            }
            popupWindow.showAtLocation(
                myParent, // Location to display popup window
                Gravity.CENTER, // Exact position of layout to display popup
                0, // X offset
                0 // Y offset
            )
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

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onBindViewHolder(holder: TrendViewHolder, position: Int) {
        val trendingResult: TrendingResult = list[position]
        holder.bind(trendingResult)
    }

    override fun getItemCount(): Int = list.size

}