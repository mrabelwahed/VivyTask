package com.vivy.di.module

import com.vivy.di.scope.AppScope
import com.vivy.network.AuthApi
import com.vivy.network.DocotorsApi
import com.vivy.repository.DoctorsRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @AppScope
    @Provides
    fun provideFeedRepository(authApi: AuthApi, doctorApi: DocotorsApi) = DoctorsRepository(authApi,doctorApi)
}