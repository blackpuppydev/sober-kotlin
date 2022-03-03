package com.nattawut.sober_kotlin.activity


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.nattawut.sober_kotlin.AppPreference
import com.nattawut.sober_kotlin.R
import com.nattawut.sober_kotlin.manager.DBManager

class SplashActivity : AppCompatActivity() {

    var dbManager:DBManager? = null



    companion object{
        const val CAMERA_PERMISSION_CODE = 101
        const val GALLERY_PERMISSION_CODE = 102
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar!!.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        AppPreference.getInstance().setSharedPreference(applicationContext)
        dbManager = DBManager(this)

        if (!AppPreference.getInstance().getMaster()){
            dbManager?.addMaster(resources)
            AppPreference.getInstance().setMaster(true)
        }

        val timer = object: CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }


            override fun onFinish() {
                //check login

                checkPermission(Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE)
                checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE,GALLERY_PERMISSION_CODE)

                if (AppPreference.getInstance().getUsername() == ""){
                    startActivity(Intent(applicationContext,LoginActivity::class.java))
                }else{
                    startActivity(Intent(applicationContext,MainActivity::class.java))
                }

            }
        }
        timer.start()
    }



    private fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(this,arrayOf(permission), requestCode)
            Toast.makeText(this, "Permission already denied", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Camera Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Camera Permission Denied", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == GALLERY_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Storage Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Storage Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }



}