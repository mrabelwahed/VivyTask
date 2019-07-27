package com.vivy.di.subcomponent

import com.vivy.di.module.SplashViewModelModule
import com.vivy.di.module.ViewModelFactoryModule
import com.vivy.di.scope.ActivityScope
import com.vivy.ui.splash.SplashActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(
    modules = [
        ViewModelFactoryModule::class,
        SplashViewModelModule::class
    ]
)
interface SplashComponent {
    fun inject(splashActivity: SplashActivity)
}