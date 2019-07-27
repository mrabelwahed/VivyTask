package com.vivy

import android.app.Application
import com.vivy.di.component.AppComponent
import com.vivy.di.component.DaggerAppComponent
import com.vivy.di.module.NetworkModule
import com.vivy.di.module.UsecaseModule
import com.vivy.di.module.RepositoryModule

class BaseApp : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        this.appComponent = this.initDagger()
    }

    private fun initDagger()  = DaggerAppComponent.builder()
        .networkModule(NetworkModule())
        .repositoryModule(RepositoryModule())
        .usecaseModule(UsecaseModule())
        .build()

}