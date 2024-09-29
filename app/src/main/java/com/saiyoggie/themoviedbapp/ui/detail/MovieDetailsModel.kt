package com.saiyoggie.themoviedbapp.ui.detail

import com.saiyoggie.themoviedbapp.data.model.details.Genre
import com.saiyoggie.themoviedbapp.data.model.details.ProductionCompany
import com.saiyoggie.themoviedbapp.data.model.details.ProductionCountry
import com.saiyoggie.themoviedbapp.data.model.details.SpokenLanguage

data class MovieDetailsModel(
    val adult: Boolean?,
    val backdropPath: String?,
    //val belongs_to_collection: String?,
    val budget: Int?,
    val genres: List<Genre>,
    val homepage: String?,
    val id: Int?,
    val imdbId: String?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val productionCompanies: List<ProductionCompany>,
    val productionCountries: List<ProductionCountry>,
    val releaseDate: String?,
    val revenue: Int?,
    val runtime: Int?,
    val spokenLanguages: List<SpokenLanguage>,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?
)