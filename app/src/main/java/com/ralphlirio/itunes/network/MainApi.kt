package com.ralphlirio.itunes.network

import com.ralphlirio.itunes.models.ApiResultResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {

    @GET("/search")
    fun getResults(
        @Query("term") term: String,
        @Query("country") country: String,
        @Query("media") media: String
    ) : Flowable<ApiResultResponse>

}