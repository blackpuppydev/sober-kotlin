package com.nattawut.sober_kotlin.model

data class HaveProfile (var name:String,var gender:String,var age:Double,var pic:String){

    override fun toString(): String {
        return "HaveProfile(name='$name', gender='$gender', age=$age, pic='$pic')"
    }
}