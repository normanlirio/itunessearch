package com.ralphlirio.itunes.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ApiResultResponse(
    @SerializedName("resultCount")
    @Expose
    var resultCount: Int? = 0,

    @SerializedName("results")
    @Expose
    var results: List<Track>? = ArrayList()


)