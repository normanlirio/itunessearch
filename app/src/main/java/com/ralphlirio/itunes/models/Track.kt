package com.ralphlirio.itunes.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Track (
        @SerializedName("trackId")
        @Expose
        var trackId: Long? = 0,

        @SerializedName("collectionArtistId")
        @Expose
        var collectionArtistId: Long? = 0,

        @SerializedName("collectionId")
        @Expose
        var collectionId: Long? = 0,

        @SerializedName("collectionName")
        @Expose
        var collectionName: String? = "",

        @SerializedName("artistName")
        @Expose
        var artistName: String? = "",

        @SerializedName("trackName")
        @Expose
        var trackName: String? = "",

        @SerializedName("collectionArtistViewUrl")
        @Expose
        var collectionArtistViewUrl: String? = "",

        @SerializedName("collectionViewUrl")
        @Expose
        var collectionViewUrl: String? = "",

        @SerializedName("trackViewUrl")
        @Expose
        var trackViewUrl: String? = "",

        @SerializedName("previewUrl")
        @Expose
        var previewUrl: String? = "",

        @SerializedName("artworkUrl30")
        @Expose
        var artworkUrl30: String? = "",

        @SerializedName("artworkUrl60")
        @Expose
        var artworkUrl60: String? = "",

        @SerializedName("artworkUrl100")
        @Expose
        var artworkUrl100: String? = "",

        @SerializedName("collectionPrice")
        @Expose
        var collectionPrice: Float? = 0.0F,

        @SerializedName("trackPrice")
        @Expose
        var trackPrice: Float? = 0.0F,

        @SerializedName("trackRentalPrice")
        @Expose
        var trackRentalPrice: Float? = 0.0F,

        @SerializedName("collectionHdPrice")
        @Expose
        var collectionHdPrice: Float? = 0.0F,

        @SerializedName("trackHdPrice")
        @Expose
        var trackHdPrice: Float? = 0.0F,

        @SerializedName("trackHdRentalPrice")
        @Expose
        var trackHdRentalPrice: Float? = 0.0F,

        @SerializedName("country")
        @Expose
        var country: String? = "",

        @SerializedName("currency")
        @Expose
        var currency: String? = "",

        @SerializedName("primaryGenreName")
        @Expose
        var primaryGenreName: String? = "",

        @SerializedName("shortDescription")
        @Expose
        var shortDescription: String? = "",

        @SerializedName("longDescription")
        @Expose
        var longDescription: String? = "",

        @SerializedName("releaseDate")
        @Expose
        var releaseDate: String? = "",

        @SerializedName("trackTimeMillis")
        @Expose
        var trackTimeMillis: Long = 0










)