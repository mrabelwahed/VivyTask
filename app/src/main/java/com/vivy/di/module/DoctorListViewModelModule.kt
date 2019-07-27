package com.vivy.di.module

import androidx.lifecycle.ViewModel
import com.vivy.ui.doctorsearch.doctorslist.DoctorListViewModel
import com.vivy.ui.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class DoctorListViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(DoctorListViewModel::class)
    internal abstract fun bindDoctorListViewModel(viewModel: DoctorListViewModel): ViewModel
}