package com.ralphlirio.itunes.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.ralphlirio.itunes.di.ViewModelProviderFactory
import com.ralphlirio.itunes.ui.main.MainViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

open class BaseFragment : DaggerFragment() {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    val sharedViewModel: SharedViewModel by activityViewModels()
    lateinit var mainViewModel: MainViewModel

    lateinit var mPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPrefs =  (requireActivity() as AppCompatActivity).getPreferences(Context.MODE_PRIVATE)
        mainViewModel = ViewModelProvider(this, providerFactory).get(MainViewModel::class.java)
    }
}