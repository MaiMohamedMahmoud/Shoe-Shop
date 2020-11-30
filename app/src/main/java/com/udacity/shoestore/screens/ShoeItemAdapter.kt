package com.udacity.shoestore.screens

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.udacity.shoestore.R
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.shoe_item.view.*

class ShoeItemAdapter(val listData: List<Shoe>) :
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
        Log.i("ShoeListFragment", "count " + listData.size)
        return listData.size
    }

    override fun onBindViewHolder(holder: ShoeItemViewHolder, position: Int) {
        holder.name.text = listData[position].name
        Log.i("ShoeListFragment Bind", listData[position].name)
    }
}