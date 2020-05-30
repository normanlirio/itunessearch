package com.ralphlirio.itunes.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ralphlirio.itunes.models.ApiResultResponse
import com.ralphlirio.itunes.repository.MainRepository
import com.ralphlirio.itunes.ui.Resource
import javax.inject.Inject

class MainViewModel @Inject constructor(val mainRepository: MainRepository): ViewModel() {

    fun subscribeTrack() : LiveData<Resource<ApiResultResponse>> {
       return mainRepository.getTracksFromApi()
    }
}