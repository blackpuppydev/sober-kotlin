package com.nattawut.sober_kotlin.manager

import android.content.Context
import android.content.res.Resources
import java.util.*

class Language {

    var languageCode:String? = null
    lateinit var locale:Locale

   fun setLanguage(resource:Resources,languageCode:String){

       locale = Locale(languageCode)
       var res = resource
       var dm = res.displayMetrics
       var conf = res.configuration
       res.updateConfiguration(conf,dm)

   }



}