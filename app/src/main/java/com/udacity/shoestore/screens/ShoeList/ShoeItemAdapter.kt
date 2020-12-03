package com.udacity.shoestore.screens.ShoeList

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.models.Shoe

class ShoeItemAdapter(val listData: List<Shoe>) :
    RecyclerView.Adapter<ShoeItemAdapter.ShoeItemViewHolder>() {

    inner class ShoeItemViewHolder(val binding: ShoeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: TextView
        val company: TextView

        init {
            name = itemView.findViewById(R.id.txt_Name)
            company = itemView.findViewById(R.id.txt_company)
        }

        fun bind(shoe: Shoe) {
            // name.text = shoe.name //this@ShoeItemAdapter.listData.get(0)
            // company.text = shoe.company
            binding.shoe = shoe
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeItemViewHolder {
        val binding = ShoeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoeItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        Log.i("ShoeListFragment", "count " + listData.size)
        return listData.size
    }

    override fun onBindViewHolder(holder: ShoeItemViewHolder, position: Int) {
        holder.bind(listData[position])
//        holder.name.text = listData[position].name
//        Log.i("ShoeListFragment Bind", listData[position].name)
    }
}