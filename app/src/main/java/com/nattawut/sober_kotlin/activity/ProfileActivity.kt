package com.nattawut.sober_kotlin.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.nattawut.sober_kotlin.R
import com.nattawut.sober_kotlin.constance.LandingPage
import com.nattawut.sober_kotlin.constance.TypeData
import com.nattawut.sober_kotlin.fragment.profile.*
import com.nattawut.sober_kotlin.listener.FragmentEvent
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.util.*

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


    //age
    var age = 0
    var date = 0
    var month = 0
    var year = 0



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
            LandingPage.START_PROFILE -> { fragment = StartProfileFragment.newInstance(firstname,lastname) }
            LandingPage.ADD_PROFILE -> {
                fragment = AddProfileFragment()
            }
            LandingPage.HAVE_PROFILE -> { fragment = HaveProfileFragment() }
            LandingPage.ADD_PROFILE2 -> { fragment = AddProfileFragment2() }
            LandingPage.ADD_PROFILE3 -> { fragment = AddProfileFragment3() }
            LandingPage.ADD_NOTE -> { fragment = AddNoteFragment.newInstance(note,"") }
            LandingPage.ADD_PIC -> {
                fragment = AddPhotoFragment()
            }
            LandingPage.ADD_PROFILE4 -> {
                fragment = AddProfileFragment4.newInstance("$firstname $lastname",gender,getAge2(dob).toString(),
                    blood,status,nation,address,edu_lv,career,congenital_dis,note) }
            LandingPage.BACK -> { super.onBackPressed() }
            LandingPage.HOME -> {
                finish()
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
            LandingPage.SL_TYPE -> {
                val intent = Intent(this, SelectTypeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
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
            TypeData.DATE -> { date = data as Int }
            TypeData.MONTH -> { month = data as Int }
            TypeData.YEAR -> { year = data as Int }
        }

    }


    private fun getAge(year: Int, month: Int, dayOfMonth: Int): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Period.between(
                LocalDate.of(year, month, dayOfMonth),
                LocalDate.now()
            ).years
        } else {
            TODO("VERSION.SDK_INT < O")
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getAge2(dobString: String): Int {

        var date: Date? = null
        val sdf = SimpleDateFormat("dd/MM/yyyy")

        try {
            date = sdf.parse(dobString)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        if (date == null) return 0

        val dob: Calendar = Calendar.getInstance()
        val today: Calendar = Calendar.getInstance()

        dob.time = date

        val year: Int = dob.get(Calendar.YEAR)
        val month: Int = dob.get(Calendar.MONTH)
        val day: Int = dob.get(Calendar.DAY_OF_MONTH)

        dob.set(year, month + 1, day)

        var age: Int = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--
        }

        return age
    }


}