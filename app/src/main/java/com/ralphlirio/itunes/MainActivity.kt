package com.ralphlirio.itunes

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.ralphlirio.itunes.ui.detail.MovieDetail
import com.ralphlirio.itunes.ui.main.MainFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        if(supportFragmentManager.fragments.size == 0){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment::class.java, null)
                .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                if(supportFragmentManager.fragments.size == 0){
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, MainFragment::class.java, null)
                        .commit()
                } else {
                    getPreferences(Context.MODE_PRIVATE).edit().clear().apply()
                    supportFragmentManager.popBackStack()
                }

                true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }

    }
}
