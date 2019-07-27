package com.vivy.ui.doctorsearch.doctorslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.bailm.vivychallenge.ui.doctorslist.paging.DoctorsDataSourceFactory
import com.vivy.data.model.Doctor
import com.vivy.domain.DoctorsUsecase
import com.vivy.repository.DoctorsRepository
import com.vivy.ui.viewmodel.BaseViewModel
import javax.inject.Inject

class DoctorListViewModel @Inject constructor(private val usecase: DoctorsUsecase) : BaseViewModel() {
    @Inject
    lateinit var doctorsRepository: DoctorsRepository
    var doctorsDataSourceFactory: DoctorsDataSourceFactory = DoctorsDataSourceFactory()
    var loadingState = Transformations.switchMap(doctorsDataSourceFactory.sourceData, { it.networkState })
    var lat: Double = 0.0
    var lng: Double = 0.0
    var doctors: LiveData<PagedList<Doctor>> = MutableLiveData<PagedList<Doctor>>()

    fun loadDoctors(searchKey:String,lat: Double, lng: Double) {
        this.lat = lat
        this.lng = lng
        doctorsDataSourceFactory.setParams(doctorsRepository, searchKey, lat, lng)
        val pagedListConfig = PagedList.Config.Builder().setEnablePlaceholders(true)
            .setInitialLoadSizeHint(10)
            .setPageSize(20).build()
        doctors = LivePagedListBuilder(doctorsDataSourceFactory, pagedListConfig)
            .build()

    }

    fun searchDoctor(searchKey: String) {
        doctorsDataSourceFactory.source.invalidate()
        loadDoctors(searchKey,lat, lng)
    }

}