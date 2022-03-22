package com.nattawut.sober_kotlin.manager

import android.graphics.Bitmap

class Personal(
    var name:String,
    var lname:String,
    var dob:String,
    var status:String,
    var address:String,
    var gender:String,
    var blood:String,
    var nation:String,
    var career:String,
    var edu_lv:String,
    var disease:String,
    var note:String,
    var pic:Bitmap
) {

    var id : Int = 0

    override fun toString(): String {
        return super.toString()
    }

}

