package com.vivy.ui.splash

import androidx.lifecycle.MutableLiveData
import com.vivy.domain.DoctorsUsecase
import com.vivy.ui.viewmodel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val usecase: DoctorsUsecase) : BaseViewModel() {
    val token = MutableLiveData<String>()

    fun getAuthToken() {
        if(!token.value.isNullOrEmpty()) return

        val tokenDispobal =  usecase.getAuthToken()
            .subscribe{
                token.value = it.access_token
            }

        compositeDisposable.add(tokenDispobal)
    }


}