package com.nattawut.sober_kotlin.adapter


import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nattawut.sober_kotlin.R

abstract class ItemOtherHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    private lateinit var titleView:TextView
    private lateinit var picView :ImageView
    private lateinit var other_select:LinearLayout

    abstract fun onClicked(position: Int)

    fun setData(title: String, img:Int){

        titleView = itemView.findViewById(R.id.title)
        picView = itemView.findViewById(R.id.pic)
        other_select = itemView.findViewById(R.id.other_select)

        titleView.text = title.toString()
        picView.setImageResource(img)

        other_select.setOnClickListener {
            onClicked(adapterPosition)
        }

    }

}