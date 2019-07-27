package com.vivy.domain

import com.vivy.repository.DoctorsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DoctorsUsecase(private val repository: DoctorsRepository) {

    fun getAuthToken() = repository.getAuthToken()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnNext { DoctorsRepository.inMemoryToken = it.access_token }


}