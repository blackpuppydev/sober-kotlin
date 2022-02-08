package com.nattawut.sober_kotlin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.nattawut.sober_kotlin.R

class LocationSettingActivity : BaseActivity() {


    lateinit var location:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_setting)

        location = findViewById(R.id.location)

        location.setOnClickListener {
            startActivity(Intent(applicationContext,MapLocationActivity::class.java))
        }


    }

}