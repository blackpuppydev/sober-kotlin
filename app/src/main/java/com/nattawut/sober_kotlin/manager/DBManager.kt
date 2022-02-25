package com.nattawut.sober_kotlin.manager

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import com.nattawut.sober_kotlin.constance.DBConst.ADMIN_COM
import com.nattawut.sober_kotlin.constance.DBConst.ADMIN_ID
import com.nattawut.sober_kotlin.constance.DBConst.ADMIN_LNAME
import com.nattawut.sober_kotlin.constance.DBConst.ADMIN_MAIL
import com.nattawut.sober_kotlin.constance.DBConst.ADMIN_NAME
import com.nattawut.sober_kotlin.constance.DBConst.ADMIN_PASSWORD
import com.nattawut.sober_kotlin.constance.DBConst.ADMIN_POS
import com.nattawut.sober_kotlin.constance.DBConst.ADMIN_USERNAME
import com.nattawut.sober_kotlin.constance.DBConst.DATABASE_NAME
import com.nattawut.sober_kotlin.constance.DBConst.DATABASE_VERSION
import com.nattawut.sober_kotlin.constance.DBConst.EMP_ID
import com.nattawut.sober_kotlin.constance.DBConst.EMP_LNAME
import com.nattawut.sober_kotlin.constance.DBConst.EMP_NAME
import com.nattawut.sober_kotlin.constance.DBConst.EMP_VAC
import com.nattawut.sober_kotlin.constance.DBConst.SYSTEM_ID
import com.nattawut.sober_kotlin.constance.DBConst.TABLE_NAME_ADMIN
import com.nattawut.sober_kotlin.constance.DBConst.TABLE_NAME_EMP


class DBManager(var context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {


    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL("CREATE TABLE $TABLE_NAME_ADMIN ( $ADMIN_ID INTEGER PRIMARY KEY AUTOINCREMENT ," +
                " $ADMIN_NAME TEXT , $ADMIN_LNAME TEXT , $ADMIN_MAIL TEXT , $ADMIN_USERNAME TEXT , $ADMIN_PASSWORD TEXT , $ADMIN_POS TEXT , $ADMIN_COM TEXT)")

        db?.execSQL("CREATE TABLE $TABLE_NAME_EMP ( $EMP_ID INTEGER PRIMARY KEY AUTOINCREMENT ," +
                " $EMP_NAME TEXT , $EMP_LNAME TEXT , $EMP_VAC TEXT)")

//        db?.execSQL("CREATE TABLE $TABLE_NAME_SCORE ( $EMP_ID INTEGER PRIMARY KEY AUTOINCREMENT ," +
//                " $EMP_NAME TEXT , $EMP_LNAME TEXT , $EMP_VAC TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    fun insertAdmin(admin:Admin){
        val dbAdmin = this.writableDatabase
        val cv = ContentValues()
//        cv.put(SYSTEM_ID,admin.id)
        cv.put(ADMIN_NAME,admin.name)
        cv.put(ADMIN_LNAME,admin.lname)
        cv.put(ADMIN_MAIL,admin.mail)
        cv.put(ADMIN_USERNAME,admin.username)
        cv.put(ADMIN_PASSWORD,admin.password)
        cv.put(ADMIN_POS,admin.pos)
        cv.put(ADMIN_COM,admin.company)
        dbAdmin?.insert(TABLE_NAME_ADMIN,null,cv)
    }

    fun getAdmin(username:String,password:String) : Boolean{
        Log.d("GET","get")
        val getAdmin = this.writableDatabase
        val resultAdmin = getAdmin?.rawQuery("select * from $TABLE_NAME_ADMIN",null)
        if (resultAdmin != null) {
            while (resultAdmin.moveToNext()){
                if(resultAdmin.getString(4).equals(username) && resultAdmin.getString(5).equals(password)){
                    return true
                }
//                Toast.makeText(context, resultAdmin.getString(1),Toast.LENGTH_SHORT).show()
//                Toast.makeText(context, resultAdmin.getString(2),Toast.LENGTH_SHORT).show()
            }
        }

        return false
    }

    fun updateAdmin(type:String,values:String){
        var dbUpdate:SQLiteDatabase = this.writableDatabase

    }

//    public fun getDataFromTable(table:String):String{
//        return "select * from $table"
//    }





}