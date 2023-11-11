package com.saiyoggie.themoviedbapp.data.remote

import androidx.lifecycle.MutableLiveData
import com.saiyoggie.themoviedbapp.ui.detail.MovieDetailsModel
import com.saiyoggie.themoviedbapp.ui.home.PopularMoviesModel

interface HomeRepository {

    suspend fun fetchPopularMovies(): MutableLiveData<MutableList<PopularMoviesModel>>
    suspend fun getMovieDetails(movieId: Int): MutableLiveData<MovieDetailsModel>

}
