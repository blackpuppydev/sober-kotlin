package com.nattawut.sober_kotlin.manager

import android.annotation.SuppressLint
import android.os.Build
import com.nattawut.sober_kotlin.AppPreference
import com.nattawut.sober_kotlin.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.util.*

class ConvertData {

    companion object{

        private var instance: ConvertData? = null

        fun getInstance(): ConvertData {
            if(instance == null) instance = ConvertData()
            return instance!!
        }
    }

    fun getAge(year: Int, month: Int, dayOfMonth: Int): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Period.between(
                LocalDate.of(year, month, dayOfMonth),
                LocalDate.now()
            ).years
        } else {
            TODO("VERSION.SDK_INT < O")
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun getAge2(dobString: String): Int {

        var date: Date? = null
        val sdf = SimpleDateFormat("dd/MM/yyyy")

        try {
            date = sdf.parse(dobString)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        if (date == null) return 0

        val dob: Calendar = Calendar.getInstance()
        val today: Calendar = Calendar.getInstance()

        dob.time = date

        val year: Int = dob.get(Calendar.YEAR)
        val month: Int = dob.get(Calendar.MONTH)
        val day: Int = dob.get(Calendar.DAY_OF_MONTH)

        dob.set(year, month + 1, day)

        var age: Int = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--
        }

        return age
    }

    fun convertStatus(status:String):String?{

        when(status){
            R.string.single.toString() -> { return "S"}
            R.string.married.toString() -> { return "M"}
            R.string.separated.toString() -> { return "D" } //divorce
            R.string.no_emp.toString() -> { return "E" } // empty
        }

        return null
    }

    fun convertGender(gender:String):String?{

        when(gender){
            R.string.male.toString() -> { return "M" }
            R.string.female.toString() -> { return "F" }
        }

        return null
    }

}