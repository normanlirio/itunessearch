package com.ralphlirio.itunes

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.ralphlirio.itunes.ui.main.MainFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * Parent activity for the Fragments
 */
class MainActivity : DaggerAppCompatActivity() {

    @set:Inject
    var hasNetwork: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        processSharedPreference()

        /**
         * check if app has internet connection
         */
        if (!hasNetwork) {
            Toast.makeText(this, getString(R.string.toast_message_1), Toast.LENGTH_LONG).show()
        }
    }

    /**
     * checks if the user has visited the app for the first time or get the timestamp of the user's last visit
     */
    private fun processSharedPreference() {
        val mPrefs = getPreferences(Context.MODE_PRIVATE)
        if (mPrefs.getBoolean(getString(R.string.has_visited), false)) {
            textView_lastVisited.text = mPrefs.getString(getString(R.string.last_visited), "")
        } else {
            textView_lastVisited.text = getString(R.string.empty_date)
        }
    }

    /**
     * Hides the last visited label when user is on Movie detail screen
     */
    fun hideLastVisited() {
        lastvisited_container.visibility = View.GONE
    }

    /**
     * Initialize the MainFragment and show the time stamp of last visited label
     */
    private fun init() {
        showLastVisited()
        if (supportFragmentManager.fragments.size == 0) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment::class.java, null)
                .commit()
        }
    }

    /**
     *  to be able to show the label outside of main activity
     */
    fun showLastVisited() {
        lastvisited_container.visibility = View.VISIBLE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                if (supportFragmentManager.fragments.size == 0) {
                    goToMainFragment()
                } else {
                    supportFragmentManager.popBackStack()
                }
                true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    private fun goToMainFragment() {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.fragment_anim_entrance, R.anim.fragment_anim_exit)
            .replace(R.id.container, MainFragment::class.java, null)
            .commit()
    }
}
