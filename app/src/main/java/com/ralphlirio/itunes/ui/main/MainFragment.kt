package com.ralphlirio.itunes.ui.main

import android.graphics.Movie
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.ralphlirio.itunes.R
import com.ralphlirio.itunes.di.ViewModelProviderFactory
import com.ralphlirio.itunes.models.Track
import com.ralphlirio.itunes.ui.Resource
import com.ralphlirio.itunes.ui.adapter.TrackAdapter
import com.ralphlirio.itunes.ui.detail.MovieDetail
import com.ralphlirio.itunes.viewmodel.BaseFragment
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_main.*
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
        setupActionbar()
        initRecyclerView()
        subscribeObservers()
        setupActionbar()

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

}