package com.nattawut.sober_kotlin.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.navigation.NavigationView
import com.nattawut.sober_kotlin.AppPreference
import com.nattawut.sober_kotlin.R
import com.nattawut.sober_kotlin.constance.TypeData
import com.nattawut.sober_kotlin.manager.DBManager
import com.nattawut.sober_kotlin.manager.Language
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.view.animation.TranslateAnimation
import kotlinx.android.synthetic.main.nav_header.*


class MainActivity : AppCompatActivity() {

    var CAMERA_REQUEST = 1
    var STORAGE_REQUEST = 2
    var LOCATION_REQUEST = 3

    var LOG :String = "MainActivity shop"


    var locale:Locale? = null
    var dbManager:DBManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar!!.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbManager = DBManager(this)

        AppPreference.getInstance().setSharedPreference(applicationContext)


        val ob = BitmapDrawable(resources, dbManager!!.getPictureProfile(AppPreference.getInstance().getUsername().toString()))
        pro_pic.background = ob

        when {
            AppPreference.getInstance().getLanguage() == "en" -> {
                setLanguage("en")
                btn_lang.setBackgroundResource(R.drawable.flag_en)
            }
            AppPreference.getInstance().getLanguage() == "th" -> {
                setLanguage("th")
                btn_lang.setBackgroundResource(R.drawable.flag_th)
            }
        }



        initView()

//        askPermission(android.Manifest.permission.CAMERA,CAMERA_REQUEST)
//        askPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE,STORAGE_REQUEST)
//        askPermission(android.Manifest.permission.ACCESS_FINE_LOCATION,LOCATION_REQUEST)

        btn_profile.setOnClickListener {
            nav_menu.visibility = View.VISIBLE
            val animate = TranslateAnimation(
                -(nav_menu.width.toFloat()),  // fromXDelta
                0F,  // toXDelta
                0F,  // fromYDelta
                0F
            )

            animate.duration = 1000
            animate.fillAfter = true
            nav_menu.startAnimation(animate)
        }

        btn_edit.setOnClickListener {
            startActivity(Intent(this,EditProfileActivity::class.java).putExtra("password",false))
        }
        

        btn_test.setOnClickListener{
            startActivity(Intent(this,ProfileActivity::class.java))
//            startActivity(Intent(this,SelectTypeActivity::class.java))
        }

        btn_data.setOnClickListener{
            startActivity(Intent(this,SelectTypeActivity::class.java))
        }

        btn_lang.setOnClickListener {
//            if(btn_lang.background.current.equals(R.drawable.flag_en)){
//                setLanguage("th")
//                btn_lang.setBackgroundResource(R.drawable.flag_th)
//            }else{
//                setLanguage("en")
//                btn_lang.setBackgroundResource(R.drawable.flag_en)
//            }

            when {
                AppPreference.getInstance().getLanguage() == "" -> {
                    setLanguage("th")
                }
                AppPreference.getInstance().getLanguage() == "th" -> {
                    setLanguage("en")
                }
                AppPreference.getInstance().getLanguage() == "en" -> {
                    setLanguage("th")
                }
            }
            
            startActivity(Intent(this,MainActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK))
            overridePendingTransition(0, 0)
        }

    }

    @SuppressLint("SetTextI18n")
    private fun initView() {

        val brand: String = Build.BRAND
        Log.d(LOG, "BRAND $brand ${Build.DEVICE} ${Build.MODEL} ${Build.DISPLAY}")

        shortName.text = "คุณ ${
            dbManager?.getTextAdmin(
                AppPreference.getInstance().getUsername().toString(),
                TypeData.FIRSTNAME
            )
        }"
        fullName.text = "${
            dbManager?.getTextAdmin(
                AppPreference.getInstance().getUsername().toString(),
                TypeData.FIRSTNAME
            )
        } " +
                "${
                    dbManager?.getTextAdmin(
                        AppPreference.getInstance().getUsername().toString(),
                        TypeData.LASTNAME
                    )
                }"

        company.text = " : ${dbManager?.getTextAdmin(AppPreference.getInstance().getUsername().toString(),TypeData.COMPANY)}"
        position.text = " : ${dbManager?.getTextAdmin(AppPreference.getInstance().getUsername().toString(),TypeData.VACATION)}"


        //show manage team
        if(AppPreference.getInstance().getUsername().toString() == "master"){
            btn_team.visibility = View.VISIBLE
        }

        nav_menu.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.change_pass -> {
                    startActivity(Intent(this, EditProfileActivity::class.java).putExtra("password",true))
                    true
                }
                R.id.setting -> {
//                    startActivity(Intent(this, LoginActivity::class.java))
                    true
                }
                R.id.logout -> {

                    AppPreference.getInstance().setUsername("")
                    AppPreference.getInstance().setPassword("")

                    startActivity(Intent(this, LoginActivity::class.java)
                        .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK))
                    true
                }
                R.id.exit -> { //check boolean 1/03/2022
                    val animate = TranslateAnimation(
                        0F,  // fromXDelta
                        -(nav_menu.width.toFloat()),  // toXDelta
                        0F,// 0F,  // fromYDelta
                        0F
                    )
                    animate.duration = 1000
                    animate.fillAfter = true
                    nav_menu.startAnimation(animate)
                    nav_menu.visibility = View.GONE


                    val intent = Intent(this, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION or Intent.FLAG_ACTIVITY_NEW_TASK)
                    overridePendingTransition(0, 0)
                    startActivity(intent)

                    false
                }
                else -> false
            }
        }

    }




//    private fun askPermission(permission :String, request_code :Int){ //add permission
//        if(ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(this, arrayOf(permission),request_code)
//        }
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        if(grantResults.isEmpty() && grantResults[0] != PackageManager.PERMISSION_GRANTED){
//            //permission failed
//        }else{
//            when(requestCode){
//                CAMERA_REQUEST -> Toast.makeText(this,"camera permission complete",Toast.LENGTH_SHORT).show()
//                STORAGE_REQUEST -> Toast.makeText(this,"storage permission complete",Toast.LENGTH_SHORT).show()
//                LOCATION_REQUEST -> Toast.makeText(this,"location permission complete",Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }

    private fun setLanguage(languageCode:String){

        locale = Locale(languageCode)
        var res = resources
        var dm = res.displayMetrics
        var conf = res.configuration
        conf.locale = locale
        res.updateConfiguration(conf,dm)

        AppPreference.getInstance().setLanguage(languageCode)

    }



}

