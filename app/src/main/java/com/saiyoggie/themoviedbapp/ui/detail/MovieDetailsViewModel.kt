package com.saiyoggie.themoviedbapp.ui.detail

import androidx.lifecycle.liveData
import com.saiyoggie.themoviedbapp.data.remote.HomeRepository
import com.saiyoggie.themoviedbapp.ui.base.BaseViewModel
import com.saiyoggie.themoviedbapp.data.remote.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repository: HomeRepository
) : BaseViewModel() {

    fun getMovieDetails(movieId:Int) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(message = "Fetching Data", data = null))
            try {
                emit(Resource.success(data = repository.getMovieDetails(movieId)))
            }catch (exception: Exception) {
                emit(
                    Resource.error(
                        data = null, message = exception.message
                            ?: "Error Occurred!"
                    )
                )
            }
        }
}