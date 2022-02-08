package com.nattawut.sober_kotlin.view

import android.content.Context
import android.util.AttributeSet
import android.widget.*
import com.nattawut.sober_kotlin.R


class CustomChoiceView(var mContext: Context, var attrs: AttributeSet)
    : LinearLayout(mContext,attrs) {

    var text_question:TextView
    var btn_nextPage:Button
    var text_answer:TextView

    init {

        inflate(mContext, R.layout.custom_content, this)

        text_question = findViewById(R.id.text_question)
        text_answer = findViewById(R.id.text_answer)
        btn_nextPage = findViewById(R.id.btn_nextPage)
    }

    fun setQuestion(question:String?){
        text_question.text = question
    }

    fun clickNextPage(num:Int){
        btn_nextPage.setOnClickListener{
            text_answer.text = "ข้อที่ $num คำตอบของคุณ คือ ${checkSelect()}"
        }
    }

    fun checkSelect():String{

        val selectID = findViewById<RadioGroup>(R.id.radioGroup).checkedRadioButtonId

        if(selectID == -1){
            return "no choice"
        }else{
            val selectChoice = findViewById<RadioButton>(selectID)
            return selectChoice.text.toString()
        }

    }




}