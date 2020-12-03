package com.udacity.shoestore.screens

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class SharedShoeListViewModel : ViewModel() {

    val shoeListLiveData = MutableLiveData<MutableList<Shoe>>()

    val ShoeList: LiveData<MutableList<Shoe>>
        get() = shoeListLiveData


    val shoeList = mutableListOf<Shoe>(
        Shoe("Shoe 1", 5.0, "Brand Name 1", "des"),
        Shoe("Shoe 2", 5.0, "Brand Name 2", "des")
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