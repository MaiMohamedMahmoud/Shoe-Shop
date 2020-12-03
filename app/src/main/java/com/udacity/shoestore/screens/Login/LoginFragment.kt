package com.udacity.shoestore.screens.Login

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import timber.log.Timber

class LoginFragment : Fragment() {
    private lateinit var loginViewModelFactory: LoginViewModelFactory
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var sharedPref: SharedPreferences
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = this.requireActivity()?.getPreferences(Context.MODE_PRIVATE) ?: return
        loginViewModelFactory = LoginViewModelFactory(sharedPref)

        loginViewModel =
            ViewModelProvider(this, loginViewModelFactory).get(LoginViewModel::class.java)

        checkLogin()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i("LoginFragment", "LoginFragment")
        //initiate binding variable using DataBindUtil
        val binding: FragmentLoginBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.btnLogin.setOnClickListener {
            loginViewModel.saveSharedPref()
            findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
        }
        binding.btnCreate.setOnClickListener {
            loginViewModel.saveSharedPref()
            findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
        }
        return binding.root
    }

    fun checkLogin() {
        val result = loginViewModel.checkLogin()
        findNavController().popBackStack()
        result.first?.let { findNavController().navigate(it, null, result.second) }

    }

}