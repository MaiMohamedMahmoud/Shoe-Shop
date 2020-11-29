package com.udacity.shoestore.screens

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.udacity.shoestore.R
import kotlinx.android.synthetic.main.shoe_item.view.*

class ShoeItemAdapter(val listData: MutableLiveData<List<String>>) :
    RecyclerView.Adapter<ShoeItemAdapter.ShoeItemViewHolder>() {

    inner class ShoeItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView
        val company: TextView

        init {
            name = itemView.findViewById(R.id.txt_Name)
            company = itemView.findViewById(R.id.txt_company)
        }

//        fun bind(position:Int) {
//            name.text = this@ShoeItemAdapter.listData.get(0)
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeItemViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.shoe_item, parent, false)
        return ShoeItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.value?.size ?: 0
    }

    override fun onBindViewHolder(holder: ShoeItemViewHolder, position: Int) {
        holder.name.text = listData.value?.get(position)
    }
}