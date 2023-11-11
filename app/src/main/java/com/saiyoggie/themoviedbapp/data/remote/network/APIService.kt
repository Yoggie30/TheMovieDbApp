package com.saiyoggie.themoviedbapp.data.remote.network

import com.saiyoggie.themoviedbapp.data.model.details.MovieDetailsResponse
import com.saiyoggie.themoviedbapp.data.model.popular.PopularMoviesResponse
import com.saiyoggie.themoviedbapp.utils.Constants
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET(Constants.PATH_POPULAR_MOVIE)
    suspend fun getPopularMovies(@Query("api_key") api_key: String,
                                 @Query("page") page: Int): Response<PopularMoviesResponse>

    @GET(Constants.PATH_MOVIE_DETAILS + "{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<MovieDetailsResponse>
}