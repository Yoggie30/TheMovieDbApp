package com.saiyoggie.themoviedbapp.ui.splash

import android.content.Intent
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.saiyoggie.themoviedbapp.R
import com.saiyoggie.themoviedbapp.ui.base.BaseActivity
import com.saiyoggie.themoviedbapp.databinding.ActivityMainBinding
import com.saiyoggie.themoviedbapp.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val splashTimeOut = 5000

    override fun getLayoutResource(): View {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated() {
        loadSplash()
    }

    private fun loadSplash() {
        Handler().postDelayed(Runnable {
            val i = Intent(this, HomeActivity::class.java)
            startActivity(i)
            finish()
        }, splashTimeOut.toLong())

        val myAnim: Animation = AnimationUtils.loadAnimation(this, R.anim.splashanimation)
        binding.splashLogo.startAnimation(myAnim)
    }
}