package com.saiyoggie.themoviedbapp.ui.home

import androidx.lifecycle.liveData
import com.saiyoggie.themoviedbapp.data.remote.HomeRepository
import com.saiyoggie.themoviedbapp.ui.base.BaseViewModel
import com.saiyoggie.themoviedbapp.data.remote.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : BaseViewModel() {

   fun fetchPopularMovies() =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(message = "Fetching Data", data = null))
            try {
                emit(Resource.success(data = repository.fetchPopularMovies()))
            } catch (exception: Exception) {
                emit(
                    Resource.error(
                        data = null, message = exception.message
                            ?: "Error Occurred!"
                    )
                )
            }
        }


}