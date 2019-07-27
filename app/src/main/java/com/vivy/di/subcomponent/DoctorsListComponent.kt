package com.vivy.di.subcomponent

import com.vivy.di.module.DoctorListViewModelModule
import com.vivy.di.module.ViewModelFactoryModule
import com.vivy.di.scope.FragmentScope
import com.vivy.ui.doctorsearch.doctorslist.DoctorsListFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(
    modules = [
        ViewModelFactoryModule::class,
        DoctorListViewModelModule::class
    ]
)
interface DoctorsListComponent {
    fun inject(doctorListFragment: DoctorsListFragment)
}