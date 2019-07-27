package com.vivy.di.module

import com.vivy.di.scope.AppScope
import com.vivy.domain.DoctorsUsecase
import com.vivy.repository.DoctorsRepository
import dagger.Module
import dagger.Provides

@Module
class UsecaseModule {
    @AppScope
    @Provides
    fun provideFeedUseCase(repository :DoctorsRepository) = DoctorsUsecase(repository)
}