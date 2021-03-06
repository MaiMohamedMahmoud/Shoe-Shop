package com.udacity.shoestore.screens.ShoeDetails

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeAddingDetailsBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.screens.SharedShoeListViewModel
import timber.log.Timber

class ShoeDetailsFragment : Fragment() {
    lateinit var shoeObj: Shoe
    lateinit var mAwesomeValidation: AwesomeValidation
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
        val shoeModel: SharedShoeListViewModel by activityViewModels()
        mAwesomeValidation = AwesomeValidation(ValidationStyle.BASIC)

        //val shoeSize :Double =  to(Double).first(binding.editSize.text.toString()).se
        binding.save.setOnClickListener {
            if (binding.editShoeName.text.isNotEmpty() && binding.editSize.text.isNotEmpty() && binding.editCompanyName.text.isNotEmpty() && binding.editDescription.text.isNotEmpty()) {
                shoeObj = Shoe(
                    binding.editShoeName.text.toString(),
                    binding.editSize.text.toString().toDouble(),
                    binding.editCompanyName.text.toString(),
                    binding.editDescription.text.toString()
                )
                shoeModel.addShoeObj(shoeObj)

                Timber.i("Save Button")
                findNavController().navigate(ShoeDetailsFragmentDirections.actionShoeDetailsFragment2ToShoeListFragment())

            } else {
                Toast.makeText(
                    context,R.string.error_msg,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        binding.cancel.setOnClickListener {
            Timber.i("Cancel Button")
            findNavController().navigate(ShoeDetailsFragmentDirections.actionShoeDetailsFragment2ToShoeListFragment())
        }
        return binding.root
    }


}