package com.nattawut.sober_kotlin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.nattawut.sober_kotlin.AppPreference
import com.nattawut.sober_kotlin.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar!!.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        AppPreference.getInstance().setSharedPreference(applicationContext)

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