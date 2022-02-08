package com.nattawut.sober_kotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nattawut.sober_kotlin.R

class HaveProfileActivity : BaseActivity() {

    lateinit var profile_list:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutInflater.inflate(R.layout.activity_have_profile,null))

        initView()

        var lm = GridLayoutManager(this,1)
        lm.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return 1
            }
        }
        profile_list.layoutManager = lm

        setAdapterProfile()


    }

    private fun setAdapterProfile() {

    }

    private fun initView(){
        profile_list = findViewById(R.id.profile_list)
    }
}