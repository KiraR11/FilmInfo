package com.example.filminfo.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.filminfo.R
import java.util.Locale

class GenreAdapter(private val onItemClick: (String?) -> Unit, private val genres: List<String>? ) : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {
    private var selectedGenre : Int? = null

    inner class GenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            itemView.findViewById<TextView>(R.id.NameGenre).text = genres!![position].replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }
            itemView.setBackgroundColor(
                if(selectedGenre == position)
                    ContextCompat.getColor(itemView.context, R.color.base_yellow)
                else
                    ContextCompat.getColor(itemView.context, R.color.base_background)
            )

            itemView.setOnClickListener {
                val previousSelectedGenre = selectedGenre

                if(selectedGenre == position)
                {
                    selectedGenre = null;
                    notifyItemChanged(position)
                    onItemClick(null)
                }
                else
                {
                    selectedGenre = position
                    previousSelectedGenre?.let { it -> notifyItemChanged(it) }
                    notifyItemChanged(selectedGenre!!)
                    onItemClick(genres[selectedGenre!!])
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false)
        return GenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = genres!!.size

}