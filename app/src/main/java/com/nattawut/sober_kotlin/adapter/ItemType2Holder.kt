package com.nattawut.sober_kotlin.adapter

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nattawut.sober_kotlin.R
import java.text.FieldPosition

public abstract class ItemType2Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    lateinit var text_count:TextView
    lateinit var text_description :TextView
    lateinit var btn_go : Button

    abstract fun onClicked(position: Int)

    public fun setData2(count:Int,sum:Int,mode:String){

        text_count = itemView.findViewById(R.id.text_count)
        text_description = itemView.findViewById(R.id.text_description)
        btn_go = itemView.findViewById(R.id.btn_go)

        text_count.text = "แบบทดสอบประเภท $count"
        text_description.text = "จำนวนข้อ $sum ข้อ ระดับ $mode"

        btn_go.setOnClickListener{
            onClicked(adapterPosition)
        }
    }


}