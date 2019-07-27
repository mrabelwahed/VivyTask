package com.bailm.vivychallenge.ui.doctorslist.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.vivy.data.model.Doctor
import com.vivy.repository.DoctorsRepository

class DoctorsDataSourceFactory : DataSource.Factory<String, Doctor>() {
    lateinit var doctorsRepository: DoctorsRepository
    lateinit var searchKey: String
    var lat: Double = 0.0
    var lng: Double = 0.0
    val sourceData = MutableLiveData<DoctorsDataSource>()
    lateinit var source: DoctorsDataSource

    override fun create(): DataSource<String, Doctor> {
        source = DoctorsDataSource(doctorsRepository, lat, lng, searchKey)
        sourceData.postValue(source)
        return source
    }

    fun setParams(doctorsRepository: DoctorsRepository, searchKey: String, lat: Double, lng: Double) {
        this.lat = lat
        this.lng = lng
        this.doctorsRepository = doctorsRepository
        this.searchKey = searchKey
    }


}