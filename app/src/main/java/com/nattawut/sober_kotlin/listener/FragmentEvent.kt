package com.nattawut.sober_kotlin.listener

interface FragmentEvent {

    fun onSuccess(page:String)
    fun onResult(data:String,type:String)

}