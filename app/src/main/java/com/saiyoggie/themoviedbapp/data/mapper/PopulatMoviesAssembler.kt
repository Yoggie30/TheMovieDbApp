package com.saiyoggie.themoviedbapp.data.mapper

import com.saiyoggie.themoviedbapp.ui.detail.MovieDetailsModel
import com.saiyoggie.themoviedbapp.ui.home.PopularMoviesModel
import com.saiyoggie.themoviedbapp.data.model.details.MovieDetailsResponse
import com.saiyoggie.themoviedbapp.data.model.popular.PopularMoviesResponse

fun assemblePopularMoviesResponse(
    response: PopularMoviesResponse
): MutableList<PopularMoviesModel> {
    val popularMoviesList = mutableListOf<PopularMoviesModel>()
    response.results.map {
        popularMoviesList.add(
            PopularMoviesModel(
                adult = it.adult,
                backdrop_path = it.backdrop_path,
                genre_ids = it.genre_ids,
                id = it.id,
                original_language = it.original_language,
                original_title = it.original_title,
                overview = it.overview,
                popularity = it.popularity,
                poster_path = it.poster_path,
                release_date = it.release_date,
                title = it.title,
                video = it.video,
                vote_average = it.vote_average,
                vote_count = it.vote_count
            )
        )
    }
    return popularMoviesList
}

fun assembleMovieDetails(response: MovieDetailsResponse): MovieDetailsModel {
    return MovieDetailsModel(
        adult = response.adult,
        backdrop_path = response.backdrop_path,
        // belongs_to_collection = response.belongs_to_collection,
        budget = response.budget,
        genres = response.genres ?: emptyList(),
        homepage = response.homepage,
        id = response.id,
        imdbId = response.imdb_id,
        originalLanguage = response.original_language,
        originalTitle = response.original_title,
        overview = response.overview,
        popularity = response.popularity,
        posterPath = response.poster_path,
        productionCompanies = response.production_companies ?: emptyList(),
        productionCountries = response.production_countries ?: emptyList(),
        releaseDate = response.release_date,
        revenue = response.revenue,
        runtime = response.runtime,
        spokenLanguages = response.spoken_languages ?: emptyList(),
        status = response.status,
        tagline = response.tagline,
        title = response.title,
        video = response.video,
        voteAverage = response.vote_average,
        voteCount = response.vote_count
    )
}









