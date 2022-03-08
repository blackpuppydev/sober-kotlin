package com.nattawut.sober_kotlin.manager

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.res.Resources
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import com.nattawut.sober_kotlin.constance.DBConst.ADMIN_COM
import com.nattawut.sober_kotlin.constance.DBConst.ADMIN_ID
import com.nattawut.sober_kotlin.constance.DBConst.ADMIN_LNAME
import com.nattawut.sober_kotlin.constance.DBConst.ADMIN_MAIL
import com.nattawut.sober_kotlin.constance.DBConst.ADMIN_NAME
import com.nattawut.sober_kotlin.constance.DBConst.ADMIN_PASSWORD
import com.nattawut.sober_kotlin.constance.DBConst.ADMIN_PIC
import com.nattawut.sober_kotlin.constance.DBConst.ADMIN_POS
import com.nattawut.sober_kotlin.constance.DBConst.ADMIN_USERNAME
import com.nattawut.sober_kotlin.constance.DBConst.DATABASE_NAME
import com.nattawut.sober_kotlin.constance.DBConst.DATABASE_VERSION
import com.nattawut.sober_kotlin.constance.DBConst.TABLE_NAME_ADMIN
import java.io.ByteArrayOutputStream
import android.graphics.BitmapFactory
import com.nattawut.sober_kotlin.AppPreference
import com.nattawut.sober_kotlin.R
import com.nattawut.sober_kotlin.constance.DBConst.PSN_ADDRESS
import com.nattawut.sober_kotlin.constance.DBConst.PSN_CAREER
import com.nattawut.sober_kotlin.constance.DBConst.PSN_CONGENITAL_DIS
import com.nattawut.sober_kotlin.constance.DBConst.PSN_DOB
import com.nattawut.sober_kotlin.constance.DBConst.PSN_EDU_LV
import com.nattawut.sober_kotlin.constance.DBConst.PSN_GENDER
import com.nattawut.sober_kotlin.constance.DBConst.PSN_ID
import com.nattawut.sober_kotlin.constance.DBConst.PSN_LNAME
import com.nattawut.sober_kotlin.constance.DBConst.PSN_NAME
import com.nattawut.sober_kotlin.constance.DBConst.PSN_STATUS
import com.nattawut.sober_kotlin.constance.DBConst.SYSTEM_ID
import com.nattawut.sober_kotlin.constance.DBConst.TABLE_NAME_PERSONAL
import com.nattawut.sober_kotlin.constance.TypeData
import com.nattawut.sober_kotlin.constance.TypeData.PSN_BLOOD
import com.nattawut.sober_kotlin.constance.TypeData.PSN_NATION


class DBManager(var context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {


    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL("CREATE TABLE $TABLE_NAME_ADMIN ( $ADMIN_ID INTEGER PRIMARY KEY AUTOINCREMENT ," +
                " $SYSTEM_ID TEXT , $ADMIN_NAME TEXT , $ADMIN_LNAME TEXT , $ADMIN_MAIL TEXT , $ADMIN_USERNAME TEXT ," +
                " $ADMIN_PASSWORD TEXT , $ADMIN_POS TEXT , $ADMIN_COM TEXT , $ADMIN_PIC BLOB)")

        db?.execSQL("CREATE TABLE $TABLE_NAME_PERSONAL ( $PSN_ID INTEGER PRIMARY KEY AUTOINCREMENT ," +
                " $PSN_NAME TEXT , $PSN_LNAME TEXT , $PSN_DOB TEXT, $PSN_NATION TEXT, $PSN_BLOOD TEXT, $PSN_STATUS TEXT," +
                " $PSN_ADDRESS TEXT, $PSN_GENDER TEXT, $PSN_CAREER TEXT, $PSN_EDU_LV TEXT, $PSN_CONGENITAL_DIS TEXT)")


//        db?.execSQL("CREATE TABLE $TABLE_NAME_SCORE ( $EMP_ID INTEGER PRIMARY KEY AUTOINCREMENT ," +
//                " $EMP_NAME TEXT , $EMP_LNAME TEXT , $EMP_VAC TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    //start app have master
    fun addMaster(resources: Resources){

        val pic:Bitmap = BitmapFactory.decodeResource(resources, R.drawable.master)

        val byteArrayOutputStream = ByteArrayOutputStream()
        pic.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream)

        val imgInByte = byteArrayOutputStream.toByteArray()

        val dbMaster = this.writableDatabase
        val cv = ContentValues()
        cv.put(SYSTEM_ID,getSystemID(6))
        cv.put(ADMIN_NAME,"Master")
        cv.put(ADMIN_LNAME,"Master")
        cv.put(ADMIN_MAIL,"master@email.com")
        cv.put(ADMIN_USERNAME,"master")
        cv.put(ADMIN_PASSWORD,"master")
        cv.put(ADMIN_POS,"master")
        cv.put(ADMIN_COM,"master")
        cv.put(ADMIN_PIC,imgInByte)
        dbMaster?.insert(TABLE_NAME_ADMIN,null,cv)
    }

    fun insertAdmin(admin:Admin){

        val byteArrayOutputStream = ByteArrayOutputStream()
        admin.picture.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream)

        val imgInByte = byteArrayOutputStream.toByteArray()

        val dbAdmin = this.writableDatabase
        val cv = ContentValues()
        cv.put(SYSTEM_ID,getSystemID(5))
        cv.put(ADMIN_NAME,admin.name)
        cv.put(ADMIN_LNAME,admin.lname)
        cv.put(ADMIN_MAIL,admin.mail)
        cv.put(ADMIN_USERNAME,admin.username)
        cv.put(ADMIN_PASSWORD,admin.password)
        cv.put(ADMIN_POS,admin.pos)
        cv.put(ADMIN_COM,admin.company)
        cv.put(ADMIN_PIC,imgInByte)
        dbAdmin?.insert(TABLE_NAME_ADMIN,null,cv)
    }

    @SuppressLint("Recycle")
    fun checkAdmin(username:String, password:String) : Boolean{
        val getAdmin = this.writableDatabase
        val resultAdmin = getAdmin?.rawQuery("select * from $TABLE_NAME_ADMIN",null)
        if (resultAdmin != null) {
            while (resultAdmin.moveToNext()){
                if(resultAdmin.getString(5).equals(username) && resultAdmin.getString(6).equals(password)){
                    return true }
            }
        }
        return false
    }


    @SuppressLint("Recycle")
    fun getPictureProfile(username:String): Bitmap? {
        var imgInByte: ByteArray? = null
        val getProfile = this.writableDatabase
        val resultProfile = getProfile?.rawQuery("select $ADMIN_PIC from $TABLE_NAME_ADMIN where $ADMIN_USERNAME = '$username'", null)
        if (resultProfile != null) {
            while (resultProfile.moveToNext()) {
                imgInByte = resultProfile.getBlob(0)
            }
        }
        return BitmapFactory.decodeByteArray(imgInByte, 0, imgInByte!!.size)
    }

    @SuppressLint("Recycle")
    fun getTextAdmin(text:String, type:String):String?{

        var result:String? = null
        var queryText:String? = null

        val getAdmin = this.writableDatabase

        when (type) {
            SYSTEM_ID -> {
                queryText = "select $SYSTEM_ID from $TABLE_NAME_ADMIN where $ADMIN_USERNAME = '$text'"
            }
            TypeData.FIRSTNAME -> {
                queryText = "select $ADMIN_NAME from $TABLE_NAME_ADMIN where $ADMIN_USERNAME = '$text'"
            }
            TypeData.LASTNAME -> {
                queryText = "select $ADMIN_LNAME from $TABLE_NAME_ADMIN where $ADMIN_USERNAME = '$text'"
            }
            TypeData.COMPANY -> {
                queryText = "select $ADMIN_COM from $TABLE_NAME_ADMIN where $ADMIN_USERNAME = '$text'"
            }
            TypeData.VACATION -> {
                queryText = "select $ADMIN_POS from $TABLE_NAME_ADMIN where $ADMIN_USERNAME = '$text'"
            }
            TypeData.USERNAME -> {
                queryText = "select $ADMIN_USERNAME from $TABLE_NAME_ADMIN where $ADMIN_USERNAME = '$text'"
            }
            TypeData.MAIL -> {
                queryText = "select $ADMIN_MAIL from $TABLE_NAME_ADMIN where $ADMIN_USERNAME = '$text'"
            }
        }

        val resultAdmin = getAdmin?.rawQuery(queryText, null)
        if(resultAdmin != null){
            while (resultAdmin.moveToNext()){
                result = resultAdmin.getString(0)
            }
        }

        return result
    }

//    fun updateAdmin(type:String,values:String){
//        var dbUpdate:SQLiteDatabase = this.writableDatabase
//
//    }

    fun updatePassword(password: String,username: String){
        val dbPassword:SQLiteDatabase = this.writableDatabase
        val cv = ContentValues()
        cv.put(ADMIN_PASSWORD,password)
        dbPassword.update(TABLE_NAME_ADMIN,cv,"admin_username=?", arrayOf(username))
    }

    fun updateProfileAdmin(username: String,
                           firstname:String,
                           lastname:String,
                           mail:String,
                           company:String,
                           position:String,pic:Bitmap,currentUsername:String){

        val byteArrayOutputStream = ByteArrayOutputStream()
        pic.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream)

        val imgInByte = byteArrayOutputStream.toByteArray()

        val dbUpdate:SQLiteDatabase = this.writableDatabase
        val cv = ContentValues()
        //system id
        cv.put(ADMIN_NAME,firstname)
        cv.put(ADMIN_LNAME,lastname)
        cv.put(ADMIN_MAIL,mail)
        cv.put(ADMIN_USERNAME,username)
        cv.put(ADMIN_PASSWORD,AppPreference.getInstance().getPassword().toString())
        cv.put(ADMIN_POS,position)
        cv.put(ADMIN_COM,company)
        cv.put(ADMIN_PIC,imgInByte)
        dbUpdate.update(TABLE_NAME_ADMIN,cv,"admin_username=?", arrayOf(currentUsername))

    }


    private fun getSystemID(length: Int) : String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }





}