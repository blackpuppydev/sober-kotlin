package com.nattawut.sober_kotlin.activity

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.nattawut.sober_kotlin.AppPreference
import com.nattawut.sober_kotlin.R
import com.nattawut.sober_kotlin.manager.Admin
import com.nattawut.sober_kotlin.manager.DBManager
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    var dbManager: DBManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        dbManager = DBManager(this)
//        dbManager!!.createTableAdmin()

        AppPreference.getInstance().setSharedPreference(applicationContext)

        initView()

    }

    private fun initView() {


        btn_confirm.setOnClickListener {

            //check database

            AppPreference.getInstance().setUsername(username.text.toString())
            AppPreference.getInstance().setPassword(password.text.toString())

            dbManager?.insertAdmin(Admin("Nattawut","Chitsaard",username.text.toString(),password.text.toString(),"master","master"))
            dbManager?.insertAdmin(Admin("Nawin","Wongwiwat","nawin.w","123","hr","com1"))


            dbManager?.getAdmin()



//
//            Toast.makeText(this,"${AppPreference.getInstance().getUsername()} : ${AppPreference.getInstance().getPassword()}",Toast.LENGTH_SHORT).show()

        }

    }
}