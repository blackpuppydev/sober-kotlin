package com.nattawut.sober_kotlin.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
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
    var firstname = ""
    var lastname = ""
    var status = ""
    var address = ""
    var career = ""
    var edu_lv = ""
    var congenital_dis = ""


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
            LandingPage.ADD_PROFILE4 -> { fragment = AddProfileFragment4() }
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

    override fun onResult(data: String, type: String) {


        when(type){
            TypeData.PSN_GENDER -> { gender = data }
            TypeData.PSN_DOB -> { dob = data }

        }
        //gender
        //birthday
        //des1,des2,des3
        //relationship
        //address
        //other
        //pic bitmap



    }
}