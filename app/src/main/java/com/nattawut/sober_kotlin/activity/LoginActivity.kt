package com.nattawut.sober_kotlin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Toast
import com.nattawut.sober_kotlin.AppPreference
import com.nattawut.sober_kotlin.R
import com.nattawut.sober_kotlin.manager.DBManager
import com.nattawut.sober_kotlin.view.dialog.LoadingDialog
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    var dbManager: DBManager? = null
    var hidePass : Boolean = true
    var loadingDialog:LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        setContentView(R.layout.activity_login)

        dbManager = DBManager(this)
        loadingDialog = LoadingDialog(this)

        AppPreference.getInstance().setSharedPreference(applicationContext)

        initView()

    }

    private fun initView() {


        btn_confirm.setOnClickListener {

            //check database
            if (dbManager!!.checkAdmin(username.text.toString(),password.text.toString())){
                AppPreference.getInstance().setUsername(username.text.toString())
                AppPreference.getInstance().setPassword(password.text.toString())

                loadingDialog?.showDialog("Please wait")

                val timer = object: CountDownTimer(2000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {}

                    override fun onFinish() {
                       loadingDialog?.hideDialog()
                       startActivity(Intent(applicationContext,MainActivity::class.java))
                    }
                }
                timer.start()
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