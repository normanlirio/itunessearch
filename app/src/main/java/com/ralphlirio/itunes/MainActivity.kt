package com.ralphlirio.itunes

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ralphlirio.itunes.ui.detail.MovieDetail
import com.ralphlirio.itunes.ui.main.MainFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject
import javax.inject.Named

class MainActivity : DaggerAppCompatActivity() {

    @set:Inject
    var hasNetwork: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        processSharedPreference()

        if(!hasNetwork) {
            Toast.makeText(this, "No Internet connection. Make sure that Wi-Fi or mobile data  is turned on, then try again.", Toast.LENGTH_LONG).show()
        }
    }

    private fun processSharedPreference() {
        val mPrefs = getPreferences(Context.MODE_PRIVATE)
        if(mPrefs.getBoolean("hasVisited", false)) {
            textView_lastVisited.text = mPrefs.getString("lastVisited", "")
        } else {
            textView_lastVisited.text = "------"
        }
    }

    fun hideLastVisited() {
        lastvisited_container.visibility = View.GONE
    }


    private fun init(){
        showLastVisited()
        if(supportFragmentManager.fragments.size == 0){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment::class.java, null)
                .commit()
        }
    }

    private fun showLastVisited() {
        lastvisited_container.visibility = View.VISIBLE
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
