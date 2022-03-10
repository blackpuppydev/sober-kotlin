package com.nattawut.sober_kotlin.listener

/**
* create by Nattawut.c 02/03/2022
**/

interface FragmentEvent {

    fun onSuccess(page:String)
    fun onResult(data:Any,type:String)

}