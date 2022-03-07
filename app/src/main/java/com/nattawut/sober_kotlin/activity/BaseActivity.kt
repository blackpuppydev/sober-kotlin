package com.nattawut.sober_kotlin.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.nattawut.sober_kotlin.R

open class BaseActivity : AppCompatActivity() {

    lateinit var btn_back:ImageButton
    lateinit var btn_next:RelativeLayout
    lateinit var content:FrameLayout
    lateinit var bg_base:RelativeLayout
    lateinit var btn_exit:ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar!!.hide()
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_base)

    }

    protected fun getBaseView(view: View?) : View {

        var v : View = layoutInflater.inflate(R.layout.activity_base,null)
        btn_back = v.findViewById(R.id.btn_back)
        btn_exit = v.findViewById(R.id.btn_exit)
        btn_next = v.findViewById(R.id.btn_next)
        content = v.findViewById(R.id.content)
        bg_base = v.findViewById(R.id.bg_base)

        btn_back.setOnClickListener{
            super.onBackPressed()
        }

        btn_exit.setOnClickListener {
            finish()
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        if(view != null) content.addView(view)

        return v
    }


    override fun setContentView(layoutResID: Int) {
        setContentView(layoutInflater.inflate(layoutResID,null))
    }

    override fun setContentView(view: View?) {
        super.setContentView(getBaseView(view))
    }






}