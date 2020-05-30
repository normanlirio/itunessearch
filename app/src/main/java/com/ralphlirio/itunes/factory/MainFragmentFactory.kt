package com.ralphlirio.itunes.factory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.ralphlirio.itunes.ui.detail.MovieDetail
import com.ralphlirio.itunes.ui.main.MainFragment

class MainFragmentFactory : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {

            MainFragment::class.java.name -> {
                super.instantiate(classLoader, className)
            }

            MovieDetail::class.java.name -> {
                super.instantiate(classLoader, className)
            }
            else -> {
                return super.instantiate(classLoader, className)
            }
        }

    }
}