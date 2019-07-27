package com.vivy.ui.splash

import android.location.Location
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.vivy.BaseApp
import com.vivy.R
import com.vivy.ui.base.BaseActivity
import com.vivy.ui.doctorsearch.DoctorSearchActivity
import com.vivy.ui.viewmodel.ViewModelFactory
import javax.inject.Inject

class SplashActivity : BaseActivity() {

    override fun getLayoutById(): Int = R.layout.activity_splash

    override fun onLocationAvailable(location: Location) {
        location?.let {
            startActivity(DoctorSearchActivity.getIntent(this))
            finish()
        }
    }

    private lateinit var splashViewModel: SplashViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun initUI() {
        (this?.applicationContext as BaseApp).appComponent.newSplashComponent().inject(this)
        splashViewModel = ViewModelProviders.of(this, viewModelFactory)[SplashViewModel::class.java]
        splashViewModel.getAuthToken()
        observeAuthToken()
    }

    private fun observeAuthToken() {
        splashViewModel.token.observe(this, Observer { token ->
            if (token != null) {
                Log.d("TTTTTT", token)
            }
        })
    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        hideSystemUI()
    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }
}