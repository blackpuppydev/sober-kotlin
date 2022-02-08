package com.nattawut.sober_kotlin.activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nattawut.sober_kotlin.R
import com.nattawut.sober_kotlin.adapter.TypeTestAdapter
import com.nattawut.sober_kotlin.model.MenuType

class SelectTypeActivity : BaseActivity() {

    lateinit var recycleType : RecyclerView
    lateinit var item : ArrayList<MenuType>
    lateinit var toolbar:RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutInflater.inflate(R.layout.activity_select_type,null))

        initView()


        var lm = GridLayoutManager(this,1)
        lm.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return 1
            }
        }
        recycleType.layoutManager = lm

        setAdapter()


    }

    private fun setAdapter() {

        item = ArrayList<MenuType>()
        item.add(MenuType("CAGE",4,"ใช้ระยะเวลาน้อย เหมาะแก่การสำรวจเบื้องต้น"))
        item.add(MenuType("AUDIT",10,"ใช้เวลานาน เหมาะกับผู้สูงอายุ การลงพื้นที่ในชุมชน"))
        item.add(MenuType("MAST",22,"ใช้เวลานาน เหมาะกับผู้สูงอายุ การลงพื้นที่ในชุมชน"))

        var adapter = object : TypeTestAdapter(item){
            override fun onSuccess(position: Int) {
                startActivity(Intent(applicationContext,QuestionActivity::class.java)
                    .putExtra("header","ชุดคำถามที่ ${position+1}"))
            }
        }
        recycleType.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
    }

    private fun initView() {

        recycleType = findViewById(R.id.recycle_type)
        toolbar = findViewById(R.id.toolbar)

        toolbar.setBackgroundColor(0x00000000)
        bg_base.background = ContextCompat.getDrawable(this, R.drawable.bg_type)

        btn_next.isGone = true


    }


}