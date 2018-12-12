package com.ceng.ozi.spacexlaunchesandroid.app.di.module

import com.ceng.ozi.spacexlaunchesandroid.BuildConfig
import com.ceng.ozi.spacexlaunchesandroid.R
import com.ceng.ozi.spacexlaunchesandroid.app.SpaceXLaunchesApplication
import com.ceng.ozi.spacexlaunchesandroid.app.api.ServiceAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
open class HttpModule {

    @Provides
    @Singleton
    open fun provideHttpLogging(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
    }

    @Provides
    @Singleton
    open fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl(SpaceXLaunchesApplication.applicationContext().getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    open fun provideApiService(retrofit: Retrofit): ServiceAPI = retrofit.create(ServiceAPI::class.java)

}