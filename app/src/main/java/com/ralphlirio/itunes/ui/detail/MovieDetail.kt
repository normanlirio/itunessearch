package com.ralphlirio.itunes.ui.detail

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.RequestManager
import com.google.gson.Gson
import com.ralphlirio.itunes.R
import com.ralphlirio.itunes.models.Track
import com.ralphlirio.itunes.viewmodel.BaseFragment
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MovieDetail : BaseFragment() {
    private var param1: String? = null
    private var param2: String? = null

    private val TAG = javaClass.simpleName

    @Inject
    lateinit var requestManager: RequestManager

    private var track: Track? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupActionbar()
        subscribeObservers()

    }

    private fun setupActionbar() {
        val appcompatActivity =  (requireActivity() as AppCompatActivity)
        appcompatActivity.supportActionBar!!.elevation = 0f
        appcompatActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        appcompatActivity.supportActionBar!!.setDisplayShowHomeEnabled(true)

    }

    private fun subscribeObservers() {
        sharedViewModel.mutableTrack.removeObservers(viewLifecycleOwner)
        sharedViewModel.mutableTrack.observe(viewLifecycleOwner, Observer {
            setUpViews(it)
            track = it
        })
    }

    private fun setUpViews(track: Track) {
        textView_long_description.text = track.longDescription
        requestManager.load(track.artworkUrl100).into(imageView_header)
        textView_trackName.text = track.trackName
        textView_genre.text = track.primaryGenreName
        textView_buyprice.text = track.currency + " " + track.trackPrice.toString()
        textView_rentprice.text = track.currency + " " + track.trackRentalPrice.toString()

        val uri: Uri = Uri.parse(
            track.previewUrl
        )
        videoView1.setVideoURI(uri)
        videoView1.requestFocus()
        videoView1.start()

        textView_artistName.text = track.artistName
        textView_releasedate.text = dateFormatter(track.releaseDate!!)
        textView_duration.text = convertToHours(track.trackTimeMillis)
    }


    private fun dateFormatter(inputDate : String) : String {
        val indexOfT= inputDate.indexOf("T")
        return inputDate.substring(0, indexOfT)
    }

    private fun convertToHours(millis : Long) : String {
        return String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
            TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
            TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
    }

    private fun processSharedPreference() {
        val prefsEditor = mPrefs.edit()
        val gson = Gson()
        val json = gson.toJson(track)
        prefsEditor.putString("previousTrack", json)
        prefsEditor.putBoolean("hasClosed", true)
        prefsEditor.commit()
    }

    override fun onStop() {
        super.onStop()
        processSharedPreference()
        Log.v(TAG, "MovieDetail: onStop ${track!!.artistName}")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        mPrefs.edit().clear().commit()
        Log.v(TAG, "MovieDetail: onDestroyView")
    }

}