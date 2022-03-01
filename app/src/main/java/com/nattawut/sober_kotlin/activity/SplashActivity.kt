package com.nattawut.sober_kotlin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.nattawut.sober_kotlin.AppPreference
import com.nattawut.sober_kotlin.R
import com.nattawut.sober_kotlin.manager.DBManager

class SplashActivity : AppCompatActivity() {

    var dbManager:DBManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar!!.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        AppPreference.getInstance().setSharedPreference(applicationContext)
        dbManager = DBManager(this)

        if (!AppPreference.getInstance().getMaster()){
            dbManager?.addMaster(resources)
            AppPreference.getInstance().setMaster(true)
        }

        val timer = object: CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                //check login

                if (AppPreference.getInstance().getUsername() == ""){
                    startActivity(Intent(applicationContext,LoginActivity::class.java))
                }else{
                    startActivity(Intent(applicationContext,MainActivity::class.java))
                }

            }
        }
        timer.start()
    }



}