package com.nattawut.sober_kotlin.adapter

import android.graphics.Color
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nattawut.sober_kotlin.R

public abstract class ItemTypeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private lateinit var textCount:TextView
    private lateinit var textDescription :TextView
    private lateinit var inputCount:TextView
    private lateinit var btn_go : RelativeLayout

    abstract fun onClicked(position: Int)

    public fun setData(type:String,count:Int,des:String){

        textCount = itemView.findViewById(R.id.text_count)
        textDescription = itemView.findViewById(R.id.text_description)
        inputCount = itemView.findViewById(R.id.input_count)
        btn_go = itemView.findViewById(R.id.btn_go)

        textCount.text = "แบบทดสอบประเภท $type"
        inputCount.text = "จำนวนข้อ $count"
        textDescription.text = "$des"

        if(adapterPosition == 0) btn_go.setBackgroundResource(R.drawable.selector_button4)
        if(adapterPosition == 1) btn_go.setBackgroundResource(R.drawable.selector_button3)

        btn_go.setOnClickListener{
            onClicked(adapterPosition)
        }
    }


}