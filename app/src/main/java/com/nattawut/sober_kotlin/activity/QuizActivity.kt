package com.nattawut.sober_kotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.nattawut.sober_kotlin.R
import com.nattawut.sober_kotlin.constance.LandingPage
import com.nattawut.sober_kotlin.fragment.profile.AddProfileFragment
import com.nattawut.sober_kotlin.fragment.profile.AddProfileFragment2
import com.nattawut.sober_kotlin.fragment.profile.HaveProfileFragment
import com.nattawut.sober_kotlin.fragment.quiz.audit.StartAuditFragment
import com.nattawut.sober_kotlin.fragment.quiz.cage.StartCageFragment
import com.nattawut.sober_kotlin.fragment.quiz.mast.StartMastFragment
import com.nattawut.sober_kotlin.listener.QuizEvent
import java.text.FieldPosition

class QuizActivity : AppCompatActivity(),QuizEvent {

    lateinit var position: String


    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar!!.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        initView()



    }

    private fun initView(){

        position = intent.getStringExtra("type").toString()

        var fragment: Fragment? = null

        when (position) {
            LandingPage.START_CAGE -> { fragment = StartCageFragment() }
            LandingPage.START_AUDIT -> { fragment = StartAuditFragment() }
            LandingPage.START_MAST -> { fragment = StartMastFragment() }
        }


        if (fragment!= null){
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_quiz,fragment,position)
                .commit()
        }

    }

    override fun onResult(score: Int, type: String, num: Int) {
        //
    }
}