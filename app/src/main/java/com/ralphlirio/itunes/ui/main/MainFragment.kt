package com.ralphlirio.itunes.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.google.gson.Gson
import com.ralphlirio.itunes.MainActivity
import com.ralphlirio.itunes.R
import com.ralphlirio.itunes.models.Track
import com.ralphlirio.itunes.ui.Resource
import com.ralphlirio.itunes.ui.adapter.TrackAdapter
import com.ralphlirio.itunes.ui.detail.MovieDetail
import com.ralphlirio.itunes.viewmodel.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*
import java.sql.Timestamp
import java.text.DateFormat
import java.util.*
import javax.inject.Inject


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class MainFragment : BaseFragment(), TrackAdapter.OnTrackClickListener {
    private var param1: String? = null
    private var param2: String? = null

    private val TAG = javaClass.simpleName

    @Inject
    lateinit var trackAdapter: TrackAdapter

    @Inject
    lateinit var requestManager: RequestManager

    @Inject
    lateinit var mainActivity: MainActivity

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
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        processSharedPreference()

        setupActionbar()
        initRecyclerView()
        subscribeObservers()
        setupActionbar()

        mainActivity.showLastVisited()

    }

    private fun processSharedPreference() {

        if(mPrefs.getBoolean("hasClosed", false)) {
            val gson = Gson()
            val json = mPrefs.getString("previousTrack", "")
            val track = gson.fromJson(json, Track::class.java)
            sharedViewModel.setMutableTrack(track)
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, MovieDetail::class.java, null)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun setupActionbar() {
        val appcompatActivity =  (requireActivity() as AppCompatActivity)
        appcompatActivity.supportActionBar!!.elevation = 0f
        appcompatActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        appcompatActivity.supportActionBar!!.setDisplayShowHomeEnabled(false)

    }

    private fun initRecyclerView() {
        recyclerView_trackList.layoutManager = LinearLayoutManager(requireContext())
        recyclerView_trackList.adapter = trackAdapter
        trackAdapter.setClickListener(this)
    }

    private fun subscribeObservers() {
        mainViewModel.subscribeTrack().removeObservers(viewLifecycleOwner)
        mainViewModel.subscribeTrack().observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Resource.Status.ERROR -> {
                    Log.v(TAG, "observe Track: Error")
                }
                Resource.Status.SUCCESS -> {
                    Log.v(TAG, "observe Track: Success")
                    saveLastVisit()
                    trackAdapter.setRequestManager(requestManager)
                    trackAdapter.setTrack(it.data!!.results!!)
                }
                Resource.Status.LOADING -> {
                    Log.v(TAG, "observe Track: Loading")
                }
            }
        })
    }

    override fun onTrackClick(track: Track) {
        sharedViewModel.setMutableTrack(track)
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, MovieDetail::class.java, null)
            .addToBackStack(null)
            .commit()
    }

    private fun saveLastVisit() {
        val prefsEditor = mPrefs.edit()
        prefsEditor.putString("lastVisited", getTimeStamp())
        prefsEditor.putBoolean("hasVisited", true)
        prefsEditor.commit()
    }

    private fun getTimeStamp(): String {
        val date = Date()
        val time: Long = date.time
        val ts = Timestamp(time)

        return DateFormat.getDateTimeInstance().format(ts)
    }
}