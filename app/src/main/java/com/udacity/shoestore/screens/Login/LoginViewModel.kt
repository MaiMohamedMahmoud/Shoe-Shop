package com.udacity.shoestore.screens.Login

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.navigation.NavOptions
import com.udacity.shoestore.R
import timber.log.Timber

class LoginViewModel(sharedPreferences: SharedPreferences) : ViewModel() {
    val sharedPref: SharedPreferences

    init {
        sharedPref = sharedPreferences
    }

    fun saveSharedPref() {
        with(sharedPref.edit()) {
            putBoolean(R.string.logInFlag.toString(), true)
            apply()
        }
    }

    fun checkLogin(): Pair<Int?, NavOptions?> {
        val flag = sharedPref.getBoolean(R.string.logInFlag.toString(), false)

        if (flag) {
            val startDestination = R.id.shoeListFragment
            val navOptions = NavOptions.Builder()
                .setPopUpTo(startDestination, true)
                .build()

            Timber.i("Login Flag" + flag)
            return Pair(startDestination, navOptions)
        }
        return Pair(null, null)
    }
}