package com.saiyoggie.themoviedbapp.ui.home

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saiyoggie.themoviedbapp.ui.base.BaseActivity
import com.saiyoggie.themoviedbapp.databinding.ActivityHomeBinding
import com.saiyoggie.themoviedbapp.data.remote.network.Resource
import com.saiyoggie.themoviedbapp.ui.detail.MovieDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeActivity : BaseActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var movieAdapter: MovieListAdapter
    private val viewModel: HomeViewModel by viewModels()

    override fun getLayoutResource(): View {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated() {
        init()
    }

    private fun init() {
        setUpRecyclerView()
        fetchPopularMovies()
    }
    private fun setUpRecyclerView() {
        movieAdapter = MovieListAdapter()
        binding.rvMoviesList.adapter= movieAdapter
        binding.rvMoviesList.apply {
            set3DItem(true)
            setAlpha(true)
            setInfinite(true)
          /*  layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
            adapter = movieAdapter*/
        }
        movieAdapter.itemSelectAction { item ->
            navigateToMovieDetails(item.id)
            /*fetchMovieDetails(item.id)
            showAlert(item.overview)*/
        }
    }
    private fun navigateToMovieDetails(movieId: Int){
        val intent = Intent(this,MovieDetailActivity::class.java).apply {
            putExtra(MOVIE_ID, movieId)
        }
        startActivity(intent)
    }

    private fun fetchPopularMovies(){
     viewModel.fetchPopularMovies()
         .observe(this) { resources ->
             when (resources.status) {
                 Resource.Status.LOADING -> {
                     showProgress()
                     resources.message?.let {
                         Timber.e("fetchPopularMovies LOADING-->$it")
                     }
                 }

                 Resource.Status.SUCCESS -> {
                     hideProgress()
                     resources.data?.value?.let {
                         movieAdapter.setItems(it)
                     }
                 }

                 Resource.Status.ERROR -> {
                     hideProgress()
                     resources.message?.let { it1 ->
                         Timber.e("fetchPopularMovies ERROR-->$it1")
                         showAlert(it1)
                     }
                 }
             }
         }
    }


    companion object {
        const val MOVIE_ID = "movieID"
    }
}
