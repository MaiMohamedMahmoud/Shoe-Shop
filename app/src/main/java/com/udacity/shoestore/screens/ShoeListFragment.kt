package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.fragment_shoe_adding_details.*
import timber.log.Timber

class ShoeListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentShoeListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        val shoeList: List<String> = listOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        val mutableShoeList: MutableLiveData<List<String>> = MutableLiveData(
            shoeList
        )

        if (savedInstanceState != null) {
            val obj = savedInstanceState.getParcelable<Shoe>("ShoeObj")
            Timber.i(obj?.name)
            shoeList.plus(obj?.name)
        }
        val manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter: ShoeItemAdapter = ShoeItemAdapter(mutableShoeList)
        //binding the recycle List
        binding.listOfShoes.layoutManager = manager
        binding.listOfShoes.adapter = adapter

        //binding add button
        binding.addShoeBtn.setOnClickListener {
            findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailsFragment)
        }

        return binding.root
    }
}