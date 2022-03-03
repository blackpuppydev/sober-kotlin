package com.nattawut.sober_kotlin.activity

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.nattawut.sober_kotlin.constance.LandingPage
import com.nattawut.sober_kotlin.constance.TypeData
import com.nattawut.sober_kotlin.fragment.profile.*
import com.nattawut.sober_kotlin.fragment.register.RegisterFragment1
import com.nattawut.sober_kotlin.fragment.register.RegisterFragment2
import com.nattawut.sober_kotlin.listener.FragmentEvent
import com.nattawut.sober_kotlin.manager.Admin
import com.nattawut.sober_kotlin.manager.DBManager
import android.graphics.drawable.BitmapDrawable


import android.graphics.drawable.Drawable
import android.graphics.BitmapFactory
import com.nattawut.sober_kotlin.R


class RegisterActivity : AppCompatActivity() , FragmentEvent{

    private var dbManager : DBManager? = null
    private var username:String = ""
    private var mail:String = ""
    private var password:String = ""
    private var firstName = ""
    private var lastName = ""
    private var company = ""
    private var vacation = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        setContentView(R.layout.activity_register)

        dbManager = DBManager(this)

        initView()

    }


    private fun initView(){
        val fragment1 = RegisterFragment1()
        supportFragmentManager.beginTransaction().add(R.id.fragment_register,fragment1).commit()

    }


    private fun openPage(page: String){

        var fragment: Fragment? = null

        when (page) {
            LandingPage.REGISTER1 -> { fragment = RegisterFragment1() }
            LandingPage.REGISTER2 -> { fragment = RegisterFragment2() }
            LandingPage.LOGIN -> {

                val pic:Bitmap = BitmapFactory.decodeResource(resources, R.drawable.get_pic)

                dbManager?.insertAdmin(Admin(
                    firstName,
                    lastName,
                    mail,
                    username,
                    password,
                    vacation,
                    company,
                    pic
                ))

                finish()
                val intent = Intent(this, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
            LandingPage.BACK -> { super.onBackPressed() }
            LandingPage.HOME -> {
                finish()
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }


        if (fragment!= null){
            //addToBackStack
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_register,fragment,page)
                .addToBackStack(page)
                .commit()
        }

    }

    override fun onSuccess(page: String) {
        openPage(page)
    }

    override fun onResult(data: String, type: String) {
        when (type){
            TypeData.USERNAME -> {username = data}
            TypeData.MAIL -> {mail = data}
            TypeData.PASSWORD -> {password = data}
            TypeData.FIRSTNAME -> {firstName = data}
            TypeData.LASTNAME -> {lastName = data}
            TypeData.COMPANY -> {company = data}
            TypeData.VACATION -> {vacation = data}
        }
    }





}