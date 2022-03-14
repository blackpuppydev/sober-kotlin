package com.nattawut.sober_kotlin.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nattawut.sober_kotlin.R

abstract  class ItemProfileHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private lateinit var titleView: TextView
    private lateinit var detailView:TextView

    abstract fun onClicked(position: Int)

    fun setData(title:String,detail:String){

        titleView = itemView.findViewById(R.id.title)
        detailView = itemView.findViewById(R.id.detail)


//        picView = itemView.findViewById(R.id.pic)
//        other_select = itemView.findViewById(R.id.other_select)
//
        titleView.text = title
        detailView.text = detail
//        picView.setImageResource(img)
//
//        other_select.setOnClickListener {
//            onClicked(adapterPosition)
//        }

    }
}