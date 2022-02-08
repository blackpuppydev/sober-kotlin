package com.nattawut.sober_kotlin.activity

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.nattawut.sober_kotlin.view.CustomChoiceView
import com.nattawut.sober_kotlin.R

class QuestionActivity : BaseActivity() {

    lateinit var header : TextView
    lateinit var customChoiceView1 : CustomChoiceView
    lateinit var customChoiceView2 : CustomChoiceView
    lateinit var customChoiceView3 : CustomChoiceView

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar!!.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        initView()


    }


    public fun initView(){

        btn_next.visibility = View.GONE

        header = findViewById(R.id.header_title)
        customChoiceView1 = findViewById(R.id.num1)
        customChoiceView2 = findViewById(R.id.num2)
        customChoiceView3 = findViewById(R.id.num3)

        header.text = intent.getStringExtra("header")

        customChoiceView1.setQuestion("ไก่กับไข่อะไรเกิดก่อนกัน")
        customChoiceView1.clickNextPage(1)
        customChoiceView2.setQuestion("หมากับแมวอะไรเกิดก่อนกัน")
        customChoiceView2.clickNextPage(2)
        customChoiceView3.setQuestion("หมูกับหมีอะไรเกิดก่อนกัน")
        customChoiceView3.clickNextPage(3)

    }
}