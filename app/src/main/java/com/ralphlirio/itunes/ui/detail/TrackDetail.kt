package com.ralphlirio.itunes.ui.detail

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.RequestManager
import com.google.gson.Gson
import com.ralphlirio.itunes.MainActivity
import com.ralphlirio.itunes.R
import com.ralphlirio.itunes.models.Track
import com.ralphlirio.itunes.viewmodel.BaseFragment
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/*
 * Fragment for the MovieDetail screen
 */
class TrackDetail : BaseFragment() {

    @Inject
    lateinit var requestManager: RequestManager

    @Inject
    lateinit var mainActivity: MainActivity

    private var track: Track? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
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

        //hide the last visited label
        mainActivity.hideLastVisited()
    }

    /**
     * Sets up the action bar displaying the back button
     */
    private fun setupActionbar() {
        val appcompatActivity = (requireActivity() as AppCompatActivity)
        appcompatActivity.supportActionBar!!.elevation = 0f
        appcompatActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        appcompatActivity.supportActionBar!!.setDisplayShowHomeEnabled(true)

    }

    /**
     * Subscribes the observers to get the information passed from the SharedViewModel
     */
    private fun subscribeObservers() {
        sharedViewModel.mutableTrack.removeObservers(viewLifecycleOwner)
        sharedViewModel.mutableTrack.observe(viewLifecycleOwner, Observer {
            setUpViews(it)
            track = it
        })
    }

    /**
     * Sets up the information for the screen
     * @param track object that contains movie information from API
     */
    private fun setUpViews(track: Track) {
        textView_long_description.text = track.longDescription
        requestManager.load(track.artworkUrl100).into(imageView_header)
        textView_trackName.text = track.trackName
        textView_genre.text = track.primaryGenreName
        textView_buyprice.text = track.currency.plus(" " + track.trackPrice)
        textView_rentprice.text = track.currency.plus(" " + track.trackRentalPrice)

        val uri: Uri = Uri.parse(track.previewUrl)
        videoView1.setVideoURI(uri)
        videoView1.requestFocus()
        videoView1.start()

        textView_artistName.text = track.artistName
        textView_releasedate.text = removeTimeFromDate(track.releaseDate!!)
        textView_duration.text = convertTime(track.trackTimeMillis)
    }

    /**
     * Removes the time section of the date string
     * @param inputDate e.g., "2018-10-18T07:00:00Z"
     * @return date without the time e.g., "2018-10-18"
     */
    private fun removeTimeFromDate(inputDate: String): String {
        val indexOfT = inputDate.indexOf("T")
        return inputDate.substring(0, indexOfT)
    }

    /**
     *  Convert the time millis to Hours, minutes, seconds for the duration of the movie
     *  @param millis amount of time in milliseconds
     *  @return readable format of time in hours, minutes and seconds
     */
    private fun convertTime(millis: Long): String {
        return String.format(
            "%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
            TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(
                TimeUnit.MILLISECONDS.toHours(
                    millis
                )
            ),
            TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(
                TimeUnit.MILLISECONDS.toMinutes(
                    millis
                )
            )
        )
    }


    /**
     * Save data to online storage when
     * user minimizes or closes the app, or goes back to main screen
     */
    override fun onStop() {
        super.onStop()
        saveDataToSharedPreferences()
    }

    /**
     * Save data to shared preferences
     */
    private fun saveDataToSharedPreferences() {
        val prefsEditor = mPrefs.edit()
        val gson = Gson()
        val json = gson.toJson(track)
        prefsEditor.putString("previousTrack", json)
        prefsEditor.putBoolean("hasClosed", true)
        prefsEditor.apply()
    }

    /**
     * Remove saved data from shared preferences when user goes back to main screen
     */
    override fun onDestroyView() {
        super.onDestroyView()
        removeDataFromSharedPreferences()
    }


    /**
     * Removes saved data from shared preferences
     */
    private fun removeDataFromSharedPreferences() {
        mPrefs.edit().remove("previousTrack").apply()
        mPrefs.edit().remove("hasClosed").apply()
    }

}