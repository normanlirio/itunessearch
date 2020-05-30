package com.ralphlirio.itunes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ralphlirio.itunes.models.Track
import javax.inject.Inject

class SharedViewModel : ViewModel() {
    val mutableTrack: MutableLiveData<Track> = MutableLiveData()

    fun setMutableTrack(track: Track) {
        mutableTrack.value = track
    }
}