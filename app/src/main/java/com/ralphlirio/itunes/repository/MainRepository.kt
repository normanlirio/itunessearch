package com.ralphlirio.itunes.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.ralphlirio.itunes.models.ApiResultResponse
import com.ralphlirio.itunes.network.MainApi
import com.ralphlirio.itunes.ui.Resource
import io.reactivex.schedulers.Schedulers

class MainRepository(private val api: MainApi) {

    private var trackList: MediatorLiveData<Resource<ApiResultResponse>> = MediatorLiveData()
    private val TAG = javaClass.simpleName

    fun getTracksFromApi(): LiveData<Resource<ApiResultResponse>> {
        trackList.value = Resource.loading(null)

        val source: LiveData<Resource<ApiResultResponse>> = LiveDataReactiveStreams.fromPublisher(
            api.getResults("star", "au", "movie")
                .onErrorReturn {
                    Log.v(TAG, "onErrorReturn: ${it.localizedMessage}")
                   var apiResultResponse = ApiResultResponse()
                    apiResultResponse.resultCount = -1
                    apiResultResponse
                }
            .map {

                if(it.resultCount!! < 0) {
                    Log.v(TAG, "getTracksFromApi: Result count -1 ${it.resultCount}")
                    Resource.error("Oops, Something went wrong", null)
                }

                Resource.success(it)
            }
                .subscribeOn(Schedulers.io())
        )
        trackList.addSource(source, Observer {
            trackList.value = it
            trackList.removeSource(source)
        })
        return trackList
    }

}
