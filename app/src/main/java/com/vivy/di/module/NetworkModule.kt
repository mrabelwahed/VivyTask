package com.vivy.di.module

import android.util.Log
import com.vivy.AUTH_URL
import com.vivy.DOCTOR_URL
import com.vivy.TIMEOUT_REQUEST
import com.vivy.di.scope.AppScope
import com.vivy.network.AuthApi
import com.vivy.network.DocotorsApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {
    @AppScope
    @Provides
    fun provideHttpLogging(): HttpLoggingInterceptor {

        val loggingInterceptor = HttpLoggingInterceptor() { message ->
            Log.d("NETWORK_LOG", message)
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @AppScope
    @Provides
    fun provideOkhttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS)
            .build()

    @AppScope
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) =
        Retrofit.Builder().client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())


    @AppScope
    @Provides
    fun provideAuthApi(builder: Retrofit.Builder) =
        builder.baseUrl(AUTH_URL).build().create(AuthApi::class.java)

    @AppScope
    @Provides
    fun provideDoctorApi(builder: Retrofit.Builder) =
        builder.baseUrl(DOCTOR_URL).build().create(DocotorsApi::class.java)
}