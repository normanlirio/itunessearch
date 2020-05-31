package com.ralphlirio.itunes.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import com.ralphlirio.itunes.models.MainApiResponse
import com.ralphlirio.itunes.network.MainApi
import com.ralphlirio.itunes.ui.Resource
import io.reactivex.schedulers.Schedulers

/**
 * Repository for API calls
 * @constructor allows to directly get the mainApi
 * @property api injected from MainModule
 *
 */
class MainRepository(private val api: MainApi) {

    private var trackList: MediatorLiveData<Resource<MainApiResponse>> = MediatorLiveData()
    private val tag = javaClass.simpleName

    /**
     * get the API Response and returns a LiveData wrapped in Resource Class for easy way to check the Status/ result of the response
     *@return the api response via LiveData
     */
    fun getTracksFromApi(): LiveData<Resource<MainApiResponse>> {
        trackList.value = Resource.loading(null)

        val source: LiveData<Resource<MainApiResponse>> = LiveDataReactiveStreams.fromPublisher(
            api.getResults("star", "au", "movie")
                .onErrorReturn {
                    Log.v(tag, "onErrorReturn: ${it.localizedMessage}")
                    val apiResultResponse = MainApiResponse()
                    apiResultResponse.resultCount = -1
                    apiResultResponse
                }
                .map {
                    if (it.resultCount!! < 0) {

                        Resource.error("Oops, Something went wrong", null)
                    }
                    Resource.success(it)
                }
                .subscribeOn(Schedulers.io())
        )
        trackList.addSource(source) {
            trackList.value = it
            trackList.removeSource(source)
        }
        return trackList
    }

}
