package com.nattawut.sober_kotlin.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.nattawut.sober_kotlin.R

class MainActivity : AppCompatActivity() {


    lateinit var btn_test : RelativeLayout
    lateinit var btn_data : RelativeLayout
//    lateinit var btn_profile : ImageButton
//    lateinit var btn_setting : ImageButton


    var CAMERA_REQUEST = 1
    var STORAGE_REQUEST = 2
    var LOCATION_REQUEST = 3

    var LOG :String = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar!!.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

//        askPermission(android.Manifest.permission.CAMERA,CAMERA_REQUEST)
//        askPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE,STORAGE_REQUEST)
//        askPermission(android.Manifest.permission.ACCESS_FINE_LOCATION,LOCATION_REQUEST)



        btn_test.setOnClickListener{
            startActivity(Intent(this,ProfileActivity::class.java))
//            startActivity(Intent(this,SelectTypeActivity::class.java))
        }

        btn_data.setOnClickListener{
            startActivity(Intent(this,SelectTypeActivity::class.java))
        }


    }

    private fun initView() {

        var brand:String = Build.BRAND
        Log.d(LOG,"BRAND $brand ${Build.DEVICE} ${Build.MODEL} ${Build.DISPLAY}")

        btn_test = findViewById(R.id.btn_test)
        btn_data = findViewById(R.id.btn_data)
//        btn_setting = findViewById(R.id.btn_setting)
//        btn_profile = findViewById(R.id.btn_profile)

//        btn_profile.setOnClickListener{
//            startActivity(Intent(applicationContext,ProfileActivity::class.java))
//        }

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



}

