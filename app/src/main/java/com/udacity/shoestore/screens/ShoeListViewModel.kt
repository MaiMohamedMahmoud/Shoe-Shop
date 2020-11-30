package com.udacity.shoestore.screens

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class SharedShoeListViewModel : ViewModel() {

    val shoeListLiveData = MutableLiveData<MutableList<Shoe>>()

    val shoeList = mutableListOf<Shoe>(
        Shoe("queen", 5.0, "Company", "des"),
        Shoe("hospital", 5.0, "Company", "des")
    )

    init {
        shoeListLiveData.value = shoeList
    }

    fun addShoeObj(shoe: Shoe) {

        shoeList.add(shoe)
        shoeListLiveData.value = shoeList

        Log.i("ShoeListFragment add", "" + shoeListLiveData.value!!.size.toString())
    }

}