package com.nattawut.sober_kotlin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.nattawut.sober_kotlin.R

class ProfileThirdActivity : BaseActivity() {

    lateinit var alert_relationship: TextView
    lateinit var btn_back: ImageButton



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_third)

        initView()

        alert_relationship.visibility = View.GONE

    }


    private fun initView(){
        alert_relationship = findViewById(R.id.alert_relationship)
        btn_back = findViewById(R.id.btn_back)

        btn_back.setOnClickListener{
            finish()
            super.onBackPressed()
        }

        btn_next.setOnClickListener{
            startActivity(Intent(applicationContext,ProfileSelectPhotoActivity::class.java))
//            startActivity(Intent(applicationContext,LocationSettingActivity::class.java))
        }

    }


    private fun getMaritalStatus():String{

        val selectIDStatus = findViewById<RadioGroup>(R.id.relationship).checkedRadioButtonId

        return if(selectIDStatus == -1){
            alert_relationship.visibility = View.VISIBLE
            "no select status"
        }else{
            alert_relationship.visibility = View.GONE
            val selectChoiceStatus = findViewById<RadioButton>(selectIDStatus)
            selectChoiceStatus.text.toString()
        }

    }


}