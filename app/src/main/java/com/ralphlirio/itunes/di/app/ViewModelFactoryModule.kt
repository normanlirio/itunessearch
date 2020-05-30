package com.ralphlirio.itunes.di.app

import androidx.lifecycle.ViewModelProvider
import com.ralphlirio.itunes.di.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory) : ViewModelProvider.Factory

}