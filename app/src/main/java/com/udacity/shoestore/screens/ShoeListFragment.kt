package com.udacity.shoestore.screens

import android.app.ActionBar
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber


class ShoeListFragment : Fragment() {
    lateinit var binding: FragmentShoeListBinding
    lateinit var sharedPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("Menu true")
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var shoeList: List<String> = listOf()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        //binding add button
        binding.addShoeBtn.setOnClickListener {
            findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailsFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val shoeModel: SharedShoeListViewModel by activityViewModels()
        val manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        var adapter: ShoeItemAdapter
        //observers:
        shoeModel.shoeListLiveData.observe(viewLifecycleOwner, Observer { list ->
            adapter = ShoeItemAdapter(list)
            Log.i("ShoeListFragment", "" + list.size)
            //binding the recycle List
            binding.listOfShoes.layoutManager = manager
            binding.listOfShoes.adapter = adapter

        })

        sharedPref = this.requireActivity()?.getPreferences(Context.MODE_PRIVATE) ?: return
        val flag = sharedPref.getBoolean(getString(R.string.logInFlag), false)

        if (!flag) {
            findNavController().popBackStack()
            val startDestination = R.id.loginFragment
            val navOptions = NavOptions.Builder()
                .setPopUpTo(startDestination, true)
                .build()
            findNavController().navigate(startDestination, null, navOptions)
            Timber.i("flag" + flag)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
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
        Timber.i("inside logout")
        with(sharedPref.edit()) {
            putBoolean(getString(R.string.logInFlag), false)
            apply()
        }
        findNavController().navigate(R.id.loginFragment)
    }

}