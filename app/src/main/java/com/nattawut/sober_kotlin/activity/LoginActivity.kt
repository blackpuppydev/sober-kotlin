package com.nattawut.sober_kotlin.activity

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Toast
import com.nattawut.sober_kotlin.AppPreference
import com.nattawut.sober_kotlin.R
import com.nattawut.sober_kotlin.manager.Admin
import com.nattawut.sober_kotlin.manager.DBManager
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    var dbManager: DBManager? = null
    var hidePass : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        setContentView(R.layout.activity_login)

        dbManager = DBManager(this)

        AppPreference.getInstance().setSharedPreference(applicationContext)

        initView()

    }

    private fun initView() {


        btn_confirm.setOnClickListener {

            //check database
            if (dbManager!!.getAdmin(username.text.toString(),password.text.toString())){
                AppPreference.getInstance().setUsername(username.text.toString())
                AppPreference.getInstance().setPassword(password.text.toString())
                startActivity(Intent(applicationContext,MainActivity::class.java))
            }else{
                alert.visibility = View.VISIBLE
            }
        }

        btn_register.setOnClickListener {
            startActivity(Intent(applicationContext,RegisterActivity::class.java))
        }

        btn_forgot.setOnClickListener {
            Toast.makeText(this,"forgot password",Toast.LENGTH_SHORT).show()
        }

        btn_hide.setOnClickListener {
            if(hidePass){
                password.transformationMethod = HideReturnsTransformationMethod.getInstance()
                hidePass = false
                btn_hide.setBackgroundResource(R.drawable.eye_close)
            } else{
                password.transformationMethod = PasswordTransformationMethod.getInstance()
                hidePass = true
                btn_hide.setBackgroundResource(R.drawable.eye_open)
            }
        }

    }



    override fun onBackPressed() {
        moveTaskToBack(true)
    }




}