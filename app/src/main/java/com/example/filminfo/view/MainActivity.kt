package com.example.filminfo.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.appbar.MaterialToolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.filminfo.model.Film
import com.example.filminfo.R
import com.example.filminfo.di.appModule
import com.example.filminfo.view.contracts.HasBackButton
import org.koin.core.context.GlobalContext.startKoin
import ua.cn.stu.navigation.contract.HasCustomTitle
import ua.cn.stu.navigation.contract.Navigator

class MainActivity : AppCompatActivity(), Navigator {

    private val currentFragment: Fragment
        get() = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)!!

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            updateUi()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val fragment : LoadingDataFragment = LoadingDataFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainerView, fragment)
            .commit()

        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
    }

    private fun updateUi() {
        val fragment = currentFragment

        val tolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        if(tolbar != null) {
            tolbar.title =
                if (fragment is HasCustomTitle)
                    fragment.getTitle()
                else
                    getString(R.string.app_name)

            supportActionBar?.setDisplayHomeAsUpEnabled(
                supportFragmentManager.backStackEntryCount > 0 &&
                fragment is HasBackButton)
        }
    }

    override fun showFilmInfoScreen(film: Film) {
        launchFragment(FilmInfoFragment.newInstance(film))
    }

    override fun showFilmsListScreen(films: List<Film>) {
        launchFragment(FilmsListFragment.newInstance(films))
    }

    override fun showLoadingDataScreen() {
        launchFragment(LoadingDataFragment())
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}