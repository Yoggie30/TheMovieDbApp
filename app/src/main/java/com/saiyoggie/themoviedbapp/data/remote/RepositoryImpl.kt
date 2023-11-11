package com.saiyoggie.themoviedbapp.data.remote

import androidx.lifecycle.MutableLiveData
import com.saiyoggie.themoviedbapp.data.mapper.assembleMovieDetails
import com.saiyoggie.themoviedbapp.data.mapper.assemblePopularMoviesResponse
import com.saiyoggie.themoviedbapp.data.remote.network.RetrofitInstance
import com.saiyoggie.themoviedbapp.ui.base.BaseRepository
import com.saiyoggie.themoviedbapp.ui.detail.MovieDetailsModel
import com.saiyoggie.themoviedbapp.ui.home.PopularMoviesModel
import com.saiyoggie.themoviedbapp.utils.Constants
import com.saiyoggie.themoviedbapp.utils.NetworkHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val networkHelper: NetworkHelper,
    private val apiHelper: RetrofitInstance
) : BaseRepository(), HomeRepository {
    private var currentPage = 1
    private var lastPage = 1

    override suspend fun fetchPopularMovies(): MutableLiveData<MutableList<PopularMoviesModel>> {
        return withContext(Dispatchers.IO) {
            val responseModel = MutableLiveData<MutableList<PopularMoviesModel>>()
            if (networkHelper.isNetworkConnected()) {
                val response =
                    apiHelper.getAPIService().getPopularMovies(Constants.API_KEY, currentPage)
                if (response.isSuccessful) {
                    val result = response.body()
                    result?.let { data ->
                        responseModel.postValue(assemblePopularMoviesResponse(data))
                    }
                    Timber.e("response --> $result}")
                } else {
                    throw Exception(errorHandling(response.code().toString()))
                }
            } else {
                throw Exception("No Internet Connection")
            }
            responseModel
        }
    }

    override suspend fun getMovieDetails(movieId: Int): MutableLiveData<MovieDetailsModel> {
        return withContext(Dispatchers.IO) {
            val responseModel = MutableLiveData<MovieDetailsModel>()
            if (networkHelper.isNetworkConnected()) {
                val response = apiHelper.getAPIService().getMovieDetails(movieId,Constants.API_KEY)
                if (response.isSuccessful) {
                    val result = response.body()
                    result?.let { data ->
                        responseModel.postValue(assembleMovieDetails(data))
                    }
                    Timber.e("response --> $result}")
                } else {
                    throw Exception(errorHandling(response.code().toString()))
                }
            } else {
                throw Exception("No Internet Connection")
            }
            responseModel
        }
    }


    suspend fun fetchNextPopularMovies() {
        currentPage++
        fetchPopularMovies()
    }

    suspend fun refreshPopularMovies() {
        currentPage = 1
        fetchPopularMovies()
    }

    fun isFirstPage(): Boolean {
        return currentPage == 1
    }

    fun isLastPage(): Boolean {
        return currentPage == lastPage
    }

}