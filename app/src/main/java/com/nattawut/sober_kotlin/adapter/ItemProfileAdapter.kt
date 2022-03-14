package com.nattawut.sober_kotlin.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nattawut.sober_kotlin.R
import com.nattawut.sober_kotlin.model.MenuDetail
import com.nattawut.sober_kotlin.model.MenuOther

abstract class ItemProfileAdapter(var item: ArrayList<MenuDetail>): RecyclerView.Adapter<ItemProfileHolder>() {

    public abstract fun onSuccess(position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemProfileHolder {

        return object : ItemProfileHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycle_detail,parent,false)){
            override fun onClicked(position: Int) {
                onSuccess(position)
            }
        }
    }

    override fun onBindViewHolder(holder: ItemProfileHolder, position: Int) {
        val items:MenuDetail = item[position]
        holder.setData(items.title,items.detail)
    }

    override fun getItemCount(): Int {
        return item.size
    }

}