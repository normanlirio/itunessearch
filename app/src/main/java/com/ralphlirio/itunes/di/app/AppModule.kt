package com.ralphlirio.itunes.di.app

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.ralphlirio.itunes.R
import com.ralphlirio.itunes.util.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRequestOptions() : RequestOptions =
        RequestOptions.placeholderOf(R.drawable.ic_itunes).error(R.drawable.ic_itunes)

    @Singleton
    @Provides
    fun provideGlideInstance(application: Application, requestOptions: RequestOptions) : RequestManager =
        Glide.with(application).setDefaultRequestOptions(requestOptions)

    @Singleton
    @Provides
    fun provideImageHeader(application: Application) : Drawable =
        ContextCompat.getDrawable(application, R.drawable.ic_apple_itunes)!!


    @Singleton
    @Provides
    fun provideRetrofitInstance() : Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}
