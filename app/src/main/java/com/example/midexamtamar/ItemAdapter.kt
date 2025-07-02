package com.example.midexamtamar

import Movie
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ItemAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<ItemAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView2)
        val nameTextView: TextView = itemView.findViewById(R.id.textView)
        val releaseDateTextView: TextView = itemView.findViewById(R.id.textView3)
        val countryTextView: TextView = itemView.findViewById(R.id.textView2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.nameTextView.text = movie.name
        holder.releaseDateTextView.text = "Release: ${movie.releaseDate}"
        holder.countryTextView.text = "Country: ${movie.country}"

        Glide.with(holder.itemView.context)
            .load(movie.imageUrl)
            .into(holder.imageView)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, AboutActivity::class.java).apply {
                putExtra("name", movie.name)
                putExtra("description", movie.description)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = movies.size
}
