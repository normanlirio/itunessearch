package com.ralphlirio.itunes.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/*
 * This class represents the attributes of the main API response
 * @property resultCount total count of the results in the response
 * @property results actual list of results
 */
class MainApiResponse(
    @SerializedName("resultCount")
    @Expose
    var resultCount: Int? = 0,

    @SerializedName("results")
    @Expose
    var results: List<Track>? = ArrayList()

)