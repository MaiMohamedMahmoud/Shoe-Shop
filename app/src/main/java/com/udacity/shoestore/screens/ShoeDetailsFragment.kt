package com.udacity.shoestore.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeAddingDetailsBinding
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeDetailsFragment : Fragment() {
    lateinit var shoeObj: Shoe
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentShoeAddingDetailsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_adding_details,
            container,
            false
        )
        //val shoeSize :Double =  to(Double).first(binding.editSize.text.toString()).se
        binding.save.setOnClickListener {
            val shoeSize: Double = binding.editSize.text.toString().toDouble()

            shoeObj = Shoe(
                binding.editShoeName.text.toString(),
                shoeSize,
                binding.editCompanyName.text.toString(),
                binding.editDescription.text.toString()
            )

            Log.i("ShoeDetailsFragment", shoeObj.company + shoeObj.name + shoeObj.size)
            Timber.i(shoeObj.company + shoeObj.name + shoeObj.size)
            findNavController().navigate(ShoeDetailsFragmentDirections.actionShoeDetailsFragment2ToShoeListFragment())

        }
        return binding.root
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("ShoeObj", shoeObj)
    }
}