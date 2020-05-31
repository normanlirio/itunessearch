package com.ralphlirio.itunes.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ralphlirio.itunes.models.ApiResultResponse
import com.ralphlirio.itunes.repository.MainRepository
import com.ralphlirio.itunes.ui.Resource
import javax.inject.Inject

/**
 * @constructor allows to get the instance of mainRepository via ViewModelFactory
 */
class MainViewModel @Inject constructor(val mainRepository: MainRepository): ViewModel() {

    /**
     * call the API in the repository class
     * @return the list from the api response
     */
    fun subscribeTrack() : LiveData<Resource<ApiResultResponse>> {
       return mainRepository.getTracksFromApi()
    }
}