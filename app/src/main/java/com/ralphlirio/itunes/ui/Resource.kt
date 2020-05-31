package com.ralphlirio.itunes.ui

import androidx.annotation.NonNull
import androidx.annotation.Nullable

/**
 * expose network status using a Resource class that encapsulate both the data and its state.
 */
class Resource<T>(
    @field:NonNull @param:NonNull val status: Status,
    @field:Nullable @param:Nullable val data: T?,
    @field:Nullable @param:Nullable val message: String?
) {

    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    companion object {
        fun <T> success(@Nullable data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(@NonNull msg: String?, @Nullable data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(@Nullable data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }

}