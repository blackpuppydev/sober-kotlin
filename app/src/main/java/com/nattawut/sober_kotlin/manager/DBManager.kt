package com.nattawut.sober_kotlin.manager

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast

val DATABASE_NAME = "customer"
val DATABASE_VERSION = 1

val EMP_ID = "emp_id"
val EMP_NAME = "emp_name"
val EMP_LNAME = "emp_lname"
val EMP_VAC = "emp_vac"

val ADMIN_ID = "admin_id"
val ADMIN_NAME = "admin_name"
val ADMIN_LNAME = "admin_lname"
val ADMIN_USERNAME = "admin_username"
val ADMIN_PASSWORD = "admin_password"
val ADMIN_VAC = "admin_vac"
val ADMIN_COM = "admin_com"

val TABLE_NAME_ADMIN = "admin"
val TABLE_NAME_EMP = "employee"


class DBManager(var context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {


    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL("CREATE TABLE $TABLE_NAME_ADMIN ( $ADMIN_ID INTEGER PRIMARY KEY AUTOINCREMENT ," +
                " $ADMIN_NAME TEXT , $ADMIN_LNAME TEXT , $ADMIN_USERNAME TEXT , $ADMIN_PASSWORD TEXT , $ADMIN_VAC TEXT , $ADMIN_COM TEXT)")

        db?.execSQL("CREATE TABLE $TABLE_NAME_EMP ( $EMP_ID INTEGER PRIMARY KEY AUTOINCREMENT ," +
                " $EMP_NAME TEXT , $EMP_LNAME TEXT , $EMP_VAC TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertData(tableName:String){

    }




    public fun insertAdmin(admin:Admin){
        val dbAdmin = this.writableDatabase
        Log.d("INSERT","insert")
        var cv = ContentValues()
        cv.put(ADMIN_NAME,admin.name)
        cv.put(ADMIN_LNAME,admin.lname)
        cv.put(ADMIN_USERNAME,admin.username)
        cv.put(ADMIN_PASSWORD,admin.password)
        cv.put(ADMIN_VAC,admin.vac)
        cv.put(ADMIN_COM,admin.company)
        val result = dbAdmin?.insert(TABLE_NAME_ADMIN,null,cv)

        if(result == (-1).toLong()){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show()
        }
    }

    fun getAdmin(){
        Log.d("GET","get")
        val getAdmin = this.writableDatabase
        val resultAdmin = getAdmin?.rawQuery("select * from $TABLE_NAME_ADMIN",null)
        if (resultAdmin != null) {
            while (resultAdmin.moveToNext()){
                Toast.makeText(context, resultAdmin.getString(1),Toast.LENGTH_SHORT).show()
                Toast.makeText(context, resultAdmin.getString(2),Toast.LENGTH_SHORT).show()
            }
        }else{
            Log.d("GET","null")
        }
    }

    fun updateAdmin(type:String,values:String){
        var dbUpdate:SQLiteDatabase = this.writableDatabase

    }

//    public fun getDataFromTable(table:String):String{
//        return "select * from $table"
//    }





}