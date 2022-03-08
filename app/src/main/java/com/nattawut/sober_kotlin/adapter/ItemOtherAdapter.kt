package com.nattawut.sober_kotlin.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nattawut.sober_kotlin.R
import com.nattawut.sober_kotlin.model.MenuOther

abstract class ItemOtherAdapter(var item: ArrayList<MenuOther>): RecyclerView.Adapter<ItemOtherHolder>() {

    private var TAG = "ItemOtherAdapter"

    public abstract fun onSuccess(position: Int)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemOtherHolder {
        Log.d(TAG,"onCreateViewHolder")
        return object : ItemOtherHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycle_profile,parent,false)){
            override fun onClicked(position: Int) {
                onSuccess(position)
            }
        }
    }

    override fun onBindViewHolder(holder: ItemOtherHolder, position: Int) {
        val items:MenuOther = item[position]
        holder.setData(items.title,items.img)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

}