package com.nattawut.sober_kotlin.manager

import android.graphics.Bitmap

class Admin(
    var name: String,
    var lname: String,
    var mail: String,
    var username: String,
    var password: String,
    var pos: String,
    var company: String,
    var picture:Bitmap
) {

    var id : Int = 0

}