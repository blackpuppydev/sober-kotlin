package com.nattawut.sober_kotlin.listener

interface QuizEvent {

    fun onResult(score:Int,type:String,num:Int)

}