package com.saiyoggie.themoviedbapp.ui.detail


import android.os.Build
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.saiyoggie.themoviedbapp.utils.TimeUtils
import com.saiyoggie.themoviedbapp.R
import com.saiyoggie.themoviedbapp.data.remote.network.Resource
import com.saiyoggie.themoviedbapp.databinding.ActivityMovieDetailBinding
import com.saiyoggie.themoviedbapp.ui.base.BaseActivity
import com.saiyoggie.themoviedbapp.ui.home.HomeActivity.Companion.MOVIE_ID
import com.saiyoggie.themoviedbapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

@AndroidEntryPoint
class MovieDetailActivity : BaseActivity() {
    private lateinit var binding: ActivityMovieDetailBinding
    private val viewModel: MovieDetailsViewModel by viewModels()

    override fun getLayoutResource(): View {
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated() {
        val movieId = intent.getIntExtra(MOVIE_ID, -1)
        if (movieId != -1) {
            fetchMovieDetails(movieId)
        }
    }

    private fun fetchMovieDetails(movieId: Int) {
        viewModel.getMovieDetails(movieId)
            .observe(this, Observer { resources ->
                when (resources.status) {
                    Resource.Status.LOADING -> {
                        showProgress()
                        resources.message?.let {
                            Timber.e("fetchMovieDetails LOADING-->$it")
                        }
                    }
                    Resource.Status.SUCCESS -> {
                        hideProgress()
                        resources.data?.value?.let {
                            Timber.e("fetchMovieDetails SUCCESS-->$it")
                            setUpData(it)
                        }
                    }
                    Resource.Status.ERROR -> {
                        hideProgress()
                        resources.message?.let { it1 ->
                            Timber.e("fetchMovieDetails ERROR-->$it1")
                            showAlert(it1)
                        }
                    }
                }
            })
    }

    private fun setUpData(data: MovieDetailsModel) {
        binding.apply {
            val posterPath = "${Constants.BASE_URL_IMAGE}${data.posterPath}"
            Glide.with(root.context)
                .load(posterPath)
                .into(moviePoster)

            tvCompanyName.text = data.productionCompanies.firstOrNull()?.name ?: "NA"
            tvRuntime.text = data.runtime?.let {
                if (it > 0) {
                    TimeUtils.formatMinutes(
                        root.context,
                        it
                    )
                } else {
                    getString(R.string.no_data_na)
                }
            } ?: getString(R.string.no_data_na)

            tvYear.text = data.releaseDate?.let {
                if (it.isNotEmpty()) {
                    LocalDate.parse(it).format(
                        DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
                            .withLocale(Locale.getDefault())
                    )
                } else {
                    getString(R.string.no_release_date)
                }
            } ?: getString(R.string.no_release_date)

            tvWebsite.text = HtmlCompat.fromHtml(
                getString(
                    R.string.visit_website_url_pattern,
                    data.homepage,
                    getString(R.string.visit_website)
                ), HtmlCompat.FROM_HTML_MODE_LEGACY
            )

            tvRating.text = data.voteAverage?.let {
                if (it > 0) it.toString() else getString(R.string.no_ratings)
            } ?: getString(R.string.no_ratings)

            tvVotes.text = data.voteCount?.let {

                if (it > 0) data.voteCount.toString() else getString(R.string.no_data_na)
            } ?: getString(R.string.no_data_na)

            tvRevenue.text = data.revenue?.let {
                getString(
                    R.string.revenue_pattern,
                    DecimalFormat("##.##").format(data.revenue / 1000000.0)
                )
            } ?: getString(R.string.no_data_na)

            tvOverview.text = data.overview?.let {
                it
            } ?: "NA"
        }
    }

}