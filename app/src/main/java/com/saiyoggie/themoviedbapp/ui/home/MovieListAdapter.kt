package com.saiyoggie.themoviedbapp.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.saiyoggie.themoviedbapp.databinding.ItemMovieCellBinding
import com.saiyoggie.themoviedbapp.utils.Constants.BASE_URL_IMAGE

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {
    private var moviesList = mutableListOf<PopularMoviesModel>()
    private var listener: ((movie: PopularMoviesModel) -> Unit)? = null

    fun setItems(items: MutableList<PopularMoviesModel>) {
        this.moviesList.clear()
        this.moviesList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            ItemMovieCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding, parent.context)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(moviesList[position])
    }


    fun itemSelectAction(listener: (movie: PopularMoviesModel) -> Unit) {
        this.listener = listener
    }


    inner class MovieViewHolder(
        private val binding: ItemMovieCellBinding,
        private val appContext: Context
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movieData: PopularMoviesModel) {
            val posterPath = "$BASE_URL_IMAGE${movieData.poster_path}"
            binding.apply {
                Glide.with(appContext)
                    .load(posterPath)
                    .into(moviePoster)
            }
            itemView.setOnClickListener {
                listener?.invoke(movieData)
            }
        }
    }
}