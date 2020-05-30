package com.ralphlirio.itunes.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "track")
data class Track (
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name ="id")
        var id: Int? = 0,

        @ColumnInfo(name ="trackId")
        @SerializedName("trackId")
        @Expose
        var trackId: Long? = 0,

        @ColumnInfo(name ="collectionArtistId")
        @SerializedName("collectionArtistId")
        @Expose
        var collectionArtistId: Long? = 0,

        @ColumnInfo(name ="collectionId")
        @SerializedName("collectionId")
        @Expose
        var collectionId: Long? = 0,

        @ColumnInfo(name ="collectionName")
        @SerializedName("collectionName")
        @Expose
        var collectionName: String? = "",

        @ColumnInfo(name ="artistName")
        @SerializedName("artistName")
        @Expose
        var artistName: String? = "",

        @ColumnInfo(name ="trackName")
        @SerializedName("trackName")
        @Expose
        var trackName: String? = "",

        @ColumnInfo(name ="collectionArtistViewUrl")
        @SerializedName("collectionArtistViewUrl")
        @Expose
        var collectionArtistViewUrl: String? = "",

        @ColumnInfo(name ="collectionViewUrl")
        @SerializedName("collectionViewUrl")
        @Expose
        var collectionViewUrl: String? = "",

        @ColumnInfo(name ="trackViewUrl")
        @SerializedName("trackViewUrl")
        @Expose
        var trackViewUrl: String? = "",

        @ColumnInfo(name ="previewUrl")
        @SerializedName("previewUrl")
        @Expose
        var previewUrl: String? = "",

        @ColumnInfo(name ="artworkUrl30")
        @SerializedName("artworkUrl30")
        @Expose
        var artworkUrl30: String? = "",

        @ColumnInfo(name ="artworkUrl60")
        @SerializedName("artworkUrl60")
        @Expose
        var artworkUrl60: String? = "",

        @ColumnInfo(name ="artworkUrl100")
        @SerializedName("artworkUrl100")
        @Expose
        var artworkUrl100: String? = "",

        @ColumnInfo(name ="collectionPrice")
        @SerializedName("collectionPrice")
        @Expose
        var collectionPrice: Float? = 0.0F,

        @ColumnInfo(name ="trackPrice")
        @SerializedName("trackPrice")
        @Expose
        var trackPrice: Float? = 0.0F,

        @ColumnInfo(name ="trackRentalPrice")
        @SerializedName("trackRentalPrice")
        @Expose
        var trackRentalPrice: Float? = 0.0F,

        @ColumnInfo(name ="collectionHdPrice")
        @SerializedName("collectionHdPrice")
        @Expose
        var collectionHdPrice: Float? = 0.0F,

        @ColumnInfo(name ="trackHdPrice")
        @SerializedName("trackHdPrice")
        @Expose
        var trackHdPrice: Float? = 0.0F,

        @ColumnInfo(name ="trackHdRentalPrice")
        @SerializedName("trackHdRentalPrice")
        @Expose
        var trackHdRentalPrice: Float? = 0.0F,

        @ColumnInfo(name ="country")
        @SerializedName("country")
        @Expose
        var country: String? = "",

        @ColumnInfo(name ="currency")
        @SerializedName("currency")
        @Expose
        var currency: String? = "",

        @ColumnInfo(name ="primaryGenreName")
        @SerializedName("primaryGenreName")
        @Expose
        var primaryGenreName: String? = "",

        @ColumnInfo(name ="shortDescription")
        @SerializedName("shortDescription")
        @Expose
        var shortDescription: String? = "",

        @ColumnInfo(name ="longDescription")
        @SerializedName("longDescription")
        @Expose
        var longDescription: String? = "",

        @ColumnInfo(name ="releaseDate")
        @SerializedName("releaseDate")
        @Expose
        var releaseDate: String? = "",

        @ColumnInfo(name ="trackTimeMillis")
        @SerializedName("trackTimeMillis")
        @Expose
        var trackTimeMillis: Long = 0










)