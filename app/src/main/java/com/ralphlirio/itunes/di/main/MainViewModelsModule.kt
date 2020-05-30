package com.ralphlirio.itunes.di.main

import androidx.lifecycle.ViewModel
import com.ralphlirio.itunes.di.ViewModelKey
import com.ralphlirio.itunes.ui.main.MainViewModel
import com.ralphlirio.itunes.viewmodel.SharedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel) : ViewModel


}