package com.vivy.ui.doctorsearch.doctorslist

import android.content.Context
import android.location.Location
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.vivy.BaseApp
import com.vivy.CURRENT_LOCATION
import com.vivy.R
import com.vivy.network.NetworkState
import com.vivy.ui.base.BaseFragment
import com.vivy.ui.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_doctor_list.*
import javax.inject.Inject

class DoctorsListFragment : BaseFragment() {

    private lateinit var doctorsListViewModel: DoctorListViewModel
    private val doctorsAdapter = DoctorsAdapter()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

     var searchKey =""
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        (activity?.applicationContext as BaseApp).appComponent
            .newDoctorsListComponent().inject(this)
        doctorsListViewModel = ViewModelProviders.of(this, viewModelFactory)[DoctorListViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI(view)
        search.setOnClickListener {
            searchKey = searchET.text.toString().trim()
            doctorsListViewModel.searchDoctor(searchKey)
        }
    }

    private fun setupView(view: View) {
        val linearLayoutManager = LinearLayoutManager(context)
        doctorList.apply {
            layoutManager = linearLayoutManager
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = doctorsAdapter
        }

    }

    private fun getDoctorsListData(location: Location) {
        doctorsListViewModel.loadDoctors(searchKey ,location.latitude, location.longitude)
        doctorsListViewModel.doctors.observe(this, Observer {
            doctorsAdapter.submitList(it)
        })
    }

    override fun getLayoutById(): Int {
        return R.layout.fragment_doctor_list
    }

    private fun initUI(view: View) {
        setupView(view)
        val location = arguments?.getParcelable<Location>(CURRENT_LOCATION)
        location?.let { getDoctorsListData(it) }
        initLoadingState()
    }

    private fun initLoadingState() {
        doctorsListViewModel.loadingState.observe(this, Observer {
            if (it == NetworkState.LOADED || it == NetworkState.LOADING) {
                hideNoData()
            } else if (it == NetworkState.NO_DATA_AVAILABLE) {
                showNoData()
            }
            doctorsAdapter.updateNetworkState(it)


        })
    }

    fun showNoData() {
        noData.visibility = View.VISIBLE
    }

    fun hideNoData() {
        noData.visibility = View.GONE
    }


}
