package com.nattawut.sober_kotlin.listener

import androidx.fragment.app.Fragment

interface ProfileEvent {

    fun onSuccess(page:String)
    fun onResult(data:String)

}