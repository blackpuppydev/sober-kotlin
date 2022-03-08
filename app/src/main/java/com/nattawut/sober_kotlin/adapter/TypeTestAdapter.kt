package com.nattawut.sober_kotlin.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nattawut.sober_kotlin.R
import com.nattawut.sober_kotlin.model.MenuOther
import com.nattawut.sober_kotlin.model.MenuType

public abstract class TypeTestAdapter(var type: ArrayList<MenuType>) : RecyclerView.Adapter<ItemTypeHolder>() { //RecyclerView.ViewHolder

    private var TAG = "TypeTestAdapter"

    public abstract fun onSuccess(position: Int)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTypeHolder {
        Log.d(TAG,"onCreateViewHolder")
//        if (viewType == 1){
            return object : ItemTypeHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycle_type,parent,false)){
                override fun onClicked(position: Int) {
                    onSuccess(position)
                }
            }
//        }else{
//            return object : ItemType2Holder(LayoutInflater.from(parent.context).inflate(R.layout.recycle_type,parent,false)){
//                override fun onClicked(position: Int) {
//
//                }
//
//            }
//        }

    }

    override fun onBindViewHolder(holder: ItemTypeHolder, position: Int) {
        Log.d(TAG,"onCreateViewHolder")
        var item:MenuType? = type.get(position)

//        if(holder is ItemTypeHolder){
            holder.setData(item!!.type,item!!.count,item!!.des)
//        }else if (holder is ItemType2Holder){
//            holder.setData2(item!!.count,item!!.sum,item!!.mode)
//        }

//        if(getItemViewType(position) == 1){
//            (holder as ItemTypeHolder).setData()
//        }


//        holder.setData(item.count,item.sum,item.mode)

    }

    override fun getItemCount(): Int {
        return type.size
    }

    override fun getItemViewType(position: Int): Int {
          return super.getItemViewType(position)
    }

//    override fun getItemViewType(position: Int): Int {
//        return if(position % 2 == 1) 1 else 2
//    }





}