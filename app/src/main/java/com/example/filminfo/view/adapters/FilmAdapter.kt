package com.example.filminfo.view.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.filminfo.model.Film
import com.example.filminfo.R
import com.squareup.picasso.Picasso

class FilmAdapter(private val onItemClick: (Film) -> Unit) : RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {
    private var currentList: List<Film> = emptyList()

    inner class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(film: Film) {
            itemView.findViewById<TextView>(R.id.NameFilm).text = film.localizedName
            var imageView = itemView.findViewById<ImageView>(R.id.HeadFilm)

            if(film.imageUrl.isNullOrEmpty())
                imageView.setImageResource(R.drawable.not_image_film)
            else
                Picasso.get().load(Uri.parse(film.imageUrl)).error(R.drawable.not_image_film).into(imageView)

            itemView.setOnClickListener { onItemClick(film) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent, false)
        return FilmViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemCount() = currentList.size

    fun submitList(newList: List<Film>) {
        currentList = newList
        notifyDataSetChanged()
    }
}