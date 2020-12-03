package com.udacity.shoestore.screens.ShoeList

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.screens.SharedShoeListViewModel
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

            //IF We Follow the requirement it will be something like this:
            //Create linearlayout dynamically and add it to the scrollView
            //inside linearLayout create textView and image and fill the data with data return from list
            /**
             * Define Linear Layout with Vertical orientation and add that view
             * to scrollview which could only contain one child
             * then add child views to this LinearLayout dynamically with the data.
            val linearLayout = LinearLayout(context)
            val linearParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
            )
            linearLayout.orientation = LinearLayout.VERTICAL
            linearLayout.layoutParams = linearParams

            binding.listOfShoes.addView(linearLayout)
            for (i in list) {


            val imageView1 = ImageView(context)
            val params1 = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
            )
            val TextView1 = TextView(context)
            val params2 = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
            )

            imageView1.layoutParams = params1
            imageView1.setImageResource(R.drawable.welocme)

            TextView1.layoutParams = params2
            TextView1.text = i.name

            linearLayout.addView(imageView1)
            linearLayout.addView(TextView1)
            }
             **/

            /**
             * RecycleView also display scrolling view but it is much more efficient
             * RecyclerView will reuse the created views to reduce memory and bind the data to that view which make app more responsiveness.
             *
             * According to The Big Nerd Ranch Guide (2015) ..
             * if you have to view 100 TextViews.creating a TextView for every item in the list could easily become unworkable. As you can imagine,
             * a list can have far more than 100 items, and it can be more complex than TextView.you can't see all the view at once so there is no need to have 100 views ready and waiting.
             * It would make far more sense to create view objects only as you need them.
             **/

            //binding the recycle List
            binding.listOfShoes.layoutManager = manager
            binding.listOfShoes.adapter = adapter

        })

        sharedPref = this.requireActivity()?.getPreferences(Context.MODE_PRIVATE) ?: return

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                logout()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun logout() {
        Timber.i("inside logout")
        with(sharedPref.edit()) {
            putBoolean(R.string.logInFlag.toString(), false)
            apply()
        }
        findNavController().popBackStack()
        findNavController().navigate(R.id.loginFragment)
    }

}