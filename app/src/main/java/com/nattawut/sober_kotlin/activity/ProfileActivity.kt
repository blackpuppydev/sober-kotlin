package com.nattawut.sober_kotlin.activity

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.nattawut.sober_kotlin.R
import com.nattawut.sober_kotlin.constance.LandingPage
import com.nattawut.sober_kotlin.constance.TypeData
import com.nattawut.sober_kotlin.fragment.profile.*
import com.nattawut.sober_kotlin.listener.FragmentEvent

class ProfileActivity : BaseActivity() , FragmentEvent {

    var fragment: Fragment? = null
    var gender = ""
    var dob = ""
    var blood = ""
    var nation = ""
    var firstname = ""
    var lastname = ""
    var status = ""
    var address = ""
    var career = ""
    var edu_lv = ""
    var congenital_dis = ""
    var note = ""
    var pic:Bitmap? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initView()

    }

    override fun onResume() {
        super.onResume()
    }

    private fun initView(){

        btn_next.visibility = View.GONE

        var fragment1 = StartProfileFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragment_profile,fragment1).commit()

    }


    private fun openPage(page:String){

        var fragment: Fragment? = null

        when (page) {
            LandingPage.ADD_PROFILE -> { fragment = AddProfileFragment() }
            LandingPage.HAVE_PROFILE -> { fragment = HaveProfileFragment() }
            LandingPage.ADD_PROFILE2 -> { fragment = AddProfileFragment2() }
            LandingPage.ADD_PROFILE3 -> { fragment = AddProfileFragment3() }
            LandingPage.ADD_NOTE -> { fragment = AddNoteFragment.newInstance(note,"") }
            LandingPage.ADD_PIC -> {
                fragment = AddPhotoFragment()
            }
            LandingPage.ADD_PROFILE4 -> {
                fragment = AddProfileFragment4.newInstance("$firstname $lastname",gender,dob,blood,
                    status,nation,address,edu_lv,career,congenital_dis,note) }
            LandingPage.BACK -> { super.onBackPressed() }
            LandingPage.HOME -> {
                finish()
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
            LandingPage.SL_TYPE -> {
                startActivity(Intent(this,SelectTypeActivity::class.java))
            }
        }


        if (fragment!= null){
            //addToBackStack
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_profile,fragment,page)
                .addToBackStack(page)
                .commit()
        }
    }

    override fun onSuccess(page: String) {
        openPage(page)
    }

    override fun onResult(data: Any, type: String) {

        when(type){
            TypeData.PSN_NAME -> { firstname = data as String }
            TypeData.PSN_LNAME -> { lastname = data as String }
            TypeData.PSN_GENDER -> { gender = data as String }
            TypeData.PSN_DOB -> { dob = data as String }
            TypeData.PSN_BLOOD -> { blood = data as String }
            TypeData.PSN_NATION -> { nation = data as String }
            TypeData.PSN_CAREER -> { career = data as String }
            TypeData.PSN_STATUS -> { status = data as String }
            TypeData.PSN_ADDRESS -> { address = data as String }
            TypeData.PSN_EDU_LV -> { edu_lv = data as String }
            TypeData.PSN_NOTE -> { note = data as String }
            TypeData.PSN_PIC -> { pic = data as Bitmap }
            TypeData.PSN_CONGENITAL_DIS -> { congenital_dis = data as String }
        }

    }
}