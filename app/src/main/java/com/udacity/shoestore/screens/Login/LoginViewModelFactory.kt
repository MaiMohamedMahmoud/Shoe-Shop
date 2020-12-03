package com.udacity.shoestore.screens.Login

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LoginViewModelFactory(val sharedPreferences: SharedPreferences) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return modelClass.
//        getConstructor(ScoreViewModel::class.java).
//        newInstance(score) as T
        return LoginViewModel(sharedPreferences) as T
    }

}