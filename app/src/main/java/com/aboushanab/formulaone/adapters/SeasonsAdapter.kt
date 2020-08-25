package com.aboushanab.formulaone.adapters

import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aboushanab.formulaone.activities.SeasonActivity
import com.aboushanab.formulaone.R
import com.aboushanab.formulaone.responses.Season
import com.aboushanab.formulaone.inflate
import kotlinx.android.synthetic.main.season_list_item.view.*


class SeasonsAdapter (private val seasons: MutableList<Season>) :
    RecyclerView.Adapter<SeasonsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.season_list_item))
    }

    override fun getItemCount(): Int {
        return seasons.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(seasons[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private lateinit var season: Season
        init {
            itemView.seasonTextView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, SeasonActivity::class.java)
                intent.putExtra("year",season.year)
                context.startActivity(intent)
            }
            itemView.infoButton.setOnClickListener{
                val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(season.url))
                itemView.context.startActivity(webIntent)
            }
        }
        fun bind(season: Season) {
            this.season = season
            itemView.seasonTextView.text = "         "+season.year
        }
    }



}
