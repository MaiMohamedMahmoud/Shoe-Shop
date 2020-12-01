package com.udacity.shoestore

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.content.edit
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.udacity.shoestore.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    lateinit var sharedPref: SharedPreferences
    lateinit var navGraph: NavGraph
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        getSupportActionBar()?.setDisplayShowTitleEnabled(true)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navGraph = navController.navInflater.inflate(R.navigation.nav_main_graph)
        sharedPref = this?.getPreferences(Context.MODE_PRIVATE) ?: return
        val flag = sharedPref.getBoolean(getString(R.string.logInFlag), false)

        if (flag) {
             navGraph.setStartDestination(R.id.shoeListFragment);
            //navController.navigate(R.id.shoeListFragment)
        } else {

             navGraph.setStartDestination(R.id.loginFragment);
            //navController.navigate(R.id.loginFragment)
        }
        navController.setGraph(navGraph);
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        val toolbar: Toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setupWithNavController(navController, appBarConfiguration)
        setSupportActionBar(toolbar);
        toolbar.showOverflowMenu();
        Timber.plant(Timber.DebugTree())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                Logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun Logout() {
        sharedPref.edit {
            putBoolean(getString(R.string.logInFlag), false)
            apply()
        }
        navController.navigate(R.id.loginFragment)
    }

    override fun onBackPressed() {
        navController.navigateUp()
        //super.onBackPressed()
    }

//    override fun onSupportNavigateUp(): Boolean {
//        return navController.navigateUp()
//    }

}
