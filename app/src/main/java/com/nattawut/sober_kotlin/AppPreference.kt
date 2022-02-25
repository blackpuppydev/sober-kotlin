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



}