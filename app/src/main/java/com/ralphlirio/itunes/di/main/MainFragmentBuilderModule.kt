package com.ralphlirio.itunes.di.main

import com.ralphlirio.itunes.ui.detail.TrackDetail
import com.ralphlirio.itunes.ui.main.MainFragment
import com.ralphlirio.itunes.viewmodel.BaseFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragment() : MainFragment

    @ContributesAndroidInjector
    abstract fun contributeTrackDetailFragment() : TrackDetail

    @ContributesAndroidInjector
    abstract fun contributeBaseFragment() : BaseFragment

}