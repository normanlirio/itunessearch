package com.ralphlirio.itunes.di.app

import com.ralphlirio.itunes.MainActivity
import com.ralphlirio.itunes.di.main.MainFragmentBuilderModule
import com.ralphlirio.itunes.di.main.MainModule
import com.ralphlirio.itunes.di.main.MainViewModelsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector (
        modules = [MainFragmentBuilderModule::class, MainModule::class, MainViewModelsModule::class]
    )
    abstract fun contributeMainActivity(): MainActivity

}