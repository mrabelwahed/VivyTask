package com.vivy.ui.doctorsearch

import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.Bundle
import com.vivy.CURRENT_LOCATION
import com.vivy.R
import com.vivy.ui.base.BaseActivity
import com.vivy.ui.doctorsearch.doctorslist.DoctorsListFragment


class DoctorSearchActivity : BaseActivity() {
    override fun onLocationAvailable(location: Location) {
        openSearchDoctors(location)
    }

    override fun getLayoutById() = R.layout.activity_main
    private val doctorsListFragment = DoctorsListFragment()

    override fun initUI() {
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, DoctorSearchActivity::class.java)
        }
    }


    private fun openSearchDoctors(location: Location) {
        val bundle = Bundle()
        bundle.putParcelable(CURRENT_LOCATION, location)
        doctorsListFragment.arguments = bundle
        supportFragmentManager.beginTransaction().add(R.id.container, doctorsListFragment).commit()
    }


}
