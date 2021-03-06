package com.nattawut.sober_kotlin

import android.content.Context
import android.content.SharedPreferences
import com.securepreferences.SecurePreferences

class AppPreference {

    lateinit var sharedPreference:SharedPreferences

    private var USERNAME = "USERNAME"
    private var APPNAME = "APP"
    private var PASSWORD = "PASSWORD"
    private var LANGUAGE = "LANGUAGE"
    private var SYS_ID = "SYS_ID"

    private var MASTER = "master"


    companion object{

        private var instance:AppPreference? = null

        fun getInstance():AppPreference {
            if(instance == null) instance = AppPreference()
            return instance!!
        }
    }

    fun setSharedPreference(context: Context){
        sharedPreference = SecurePreferences(context, "1234", APPNAME)
    }

    fun setUsername(username:String){
        sharedPreference.edit().putString(USERNAME,username).apply()
    }

    fun getUsername(): String? {
        return sharedPreference.getString(USERNAME, "")
    }

    fun setPassword(password:String){
        sharedPreference.edit().putString(PASSWORD,password).apply()
    }

    fun getPassword(): String? {
        return sharedPreference.getString(PASSWORD, "")
    }

    fun setLanguage(language:String){
        sharedPreference.edit().putString(LANGUAGE,language).apply()
    }

    fun getLanguage():String?{
        return sharedPreference.getString(LANGUAGE,"")
    }

    fun setMaster(status:Boolean){
        sharedPreference.edit().putBoolean(MASTER,status).apply()
    }

    fun getMaster(): Boolean {
        return sharedPreference.getBoolean(MASTER,false)
    }

    fun setSystemID(systemID:String){
        sharedPreference.edit().putString(SYS_ID,systemID).apply()
    }

    fun getSystemID(): String? {
        return sharedPreference.getString(SYS_ID, "")
    }





}