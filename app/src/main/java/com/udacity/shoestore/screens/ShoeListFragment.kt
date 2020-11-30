package com.udacity.shoestore.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe

class ShoeListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var shoeList: List<String> = listOf()
        val binding: FragmentShoeListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

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

        }
        )
        //binding add button
        binding.addShoeBtn.setOnClickListener {
            findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailsFragment)
        }
        return binding.root
    }
}