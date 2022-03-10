package com.nattawut.sober_kotlin.activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.nattawut.sober_kotlin.R
import com.nattawut.sober_kotlin.constance.LandingPage
import com.nattawut.sober_kotlin.constance.TypeData
import com.nattawut.sober_kotlin.fragment.edit.EditProfileFragment1
import com.nattawut.sober_kotlin.fragment.edit.EditProfileFragment2
import com.nattawut.sober_kotlin.fragment.edit.PasswordFragment
import com.nattawut.sober_kotlin.fragment.register.RegisterFragment1
import com.nattawut.sober_kotlin.fragment.register.RegisterFragment2
import com.nattawut.sober_kotlin.listener.FragmentEvent
import com.nattawut.sober_kotlin.manager.Admin
import com.nattawut.sober_kotlin.manager.DBManager

class EditProfileActivity : AppCompatActivity(), FragmentEvent {

    private var dbManager : DBManager? = null
    private var username:String = ""
    private var mail:String = ""
    private var firstName = ""
    private var lastName = ""
    private var company = ""
    private var vacation = ""
    private var change_pass = false

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar!!.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        dbManager = DBManager(this)

        change_pass = intent.getBooleanExtra("password",false)

        initView()


    }

    private fun initView(){

        if (change_pass){
            val fragment1 = PasswordFragment()
            supportFragmentManager.beginTransaction().add(R.id.fragment_edit,fragment1).commit()
        }else{
            val fragment1 = EditProfileFragment1()
            supportFragmentManager.beginTransaction().add(R.id.fragment_edit,fragment1).commit()
        }


    }


    override fun onResume() {
        super.onResume()
    }


    private fun openPage(page: String){

        var fragment: Fragment? = null

        when (page) {
            LandingPage.EDIT_PROFILE1 -> { fragment = EditProfileFragment1() }
            LandingPage.EDIT_PROFILE2 -> { fragment = EditProfileFragment2() }
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
                .replace(R.id.fragment_edit,fragment,page)
                .addToBackStack(page)
                .commit()
        }

    }


    override fun onSuccess(page: String) {
        openPage(page)
    }

    override fun onResult(data: Any, type: String) {
        when (type){
            TypeData.USERNAME -> {username = data as String }
            TypeData.MAIL -> {mail = data as String }
            TypeData.FIRSTNAME -> {firstName = data as String }
            TypeData.LASTNAME -> {lastName = data as String }
            TypeData.COMPANY -> {company = data as String }
            TypeData.VACATION -> {vacation = data as String }
        }
    }
}