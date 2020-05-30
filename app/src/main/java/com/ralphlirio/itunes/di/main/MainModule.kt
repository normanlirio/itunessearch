package com.ralphlirio.itunes.di.main

import com.ralphlirio.itunes.network.MainApi
import com.ralphlirio.itunes.repository.MainRepository
import com.ralphlirio.itunes.ui.adapter.TrackAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    @Provides
    fun provideMainApi(retrofit: Retrofit) : MainApi = retrofit.create(MainApi::class.java )

    @Provides
    fun provideMainReposotory(api: MainApi) : MainRepository = MainRepository(api)

    @Provides
    fun provideTrackAdapter() : TrackAdapter = TrackAdapter()

}