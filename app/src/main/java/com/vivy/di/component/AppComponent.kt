package com.vivy.di.component

import com.vivy.di.module.*
import com.vivy.di.scope.AppScope
import com.vivy.di.subcomponent.DoctorsListComponent
import com.vivy.di.subcomponent.SplashComponent
import dagger.Component

@AppScope
@Component(
    modules = [
        NetworkModule::class,
        UsecaseModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {
    fun newDoctorsListComponent(): DoctorsListComponent
    fun newSplashComponent():SplashComponent
}