package com.saiyoggie.themoviedbapp.data.model.popular

data class PopularMoviesResponse(
    val page: Int,
    val results: List<PopularMovies>,
    val totalPages: Int,
    val totalResults: Int
)
