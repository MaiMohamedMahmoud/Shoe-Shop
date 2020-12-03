package com.udacity.shoestore.screens.Login

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
    private lateinit var binding: FragmentLoginBinding
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.btnLogin.setOnClickListener {
            login()
        }
        binding.btnCreate.setOnClickListener {
            login()
        }
        return binding.root
    }

    private fun login() {
        if (binding.editEmail.text.isNotEmpty() && binding.editPassword.text.isNotEmpty()) {
            loginViewModel.saveSharedPref()
            findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
        } else {
            Toast.makeText(
                context, R.string.error_msg,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun checkLogin() {

        val result = loginViewModel.checkLogin()
        result.first?.let {
            findNavController().popBackStack()
            findNavController().navigate(it, null, result.second)
        }


    }

}