package com.nattawut.sober_kotlin.activity

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.nattawut.sober_kotlin.R
import android.graphics.BitmapFactory
import java.util.*
import java.util.Calendar.getInstance


class ProfileSecondActivity : BaseActivity() {

    //Calendar
    var calendar:Calendar = getInstance()

    lateinit var name:TextView
    lateinit var dialog:Dialog
    lateinit var birthday:LinearLayout
    lateinit var btn_back:ImageButton
    lateinit var showBirthday:TextView

    //alert
    lateinit var alert_gender:TextView
    lateinit var alert_birthday:TextView
//    lateinit var alert_relationship:TextView
//    lateinit var alert_disease:TextView
    lateinit var disease1:Spinner
    lateinit var disease2:Spinner
    lateinit var disease3:Spinner
    lateinit var disease4:Spinner


    //values choice
    var daySelect:Int = calendar.get(Calendar.DAY_OF_MONTH)
    var monthSelect :Int = calendar.get(Calendar.MONTH)+1
    var yearSelect:Int = calendar.get(Calendar.YEAR)


    var CAMERA_PERM_CODE = 101
    var GALLERY_PERM_CODE = 102
    var CAMERA_REQUEST_CODE = 103
    var GALLERY_REQUEST_CODE = 104

    var dis1 = ""
    var dis2 = ""
    var dis3 = ""
    var dis4 = ""

    var goToNext = true



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_second)

        initView()
//        setAdapterBirth()

        name.setText(intent.getStringExtra("name"))
        alert_gender.visibility = View.GONE
        alert_birthday.visibility = View.GONE
//        alert_relationship.visibility = View.GONE
//        alert_disease.visibility = View.GONE







    }

//    private fun setAdapterBirth() {
//
//        var adapter : ArrayAdapter<String> = ArrayAdapter(this.applicationContext,R.layout.support_simple_spinner_dropdown_item,all_month)
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        month.adapter = adapter
//        month.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>?,view: View?,position: Int,id: Long ) {
//                monthSelect = parent?.selectedItem.toString()
////                Toast.makeText(applicationContext, parent?.selectedItem.toString(),Toast.LENGTH_SHORT).show()
//            }
//            override fun onNothingSelected(parent: AdapterView<*>?) {}
//        }
//
//    }




    private fun initView() {

        name = findViewById(R.id.nameAdd)
        birthday = findViewById(R.id.getBirthday)
        btn_back = findViewById(R.id.btn_back)
        showBirthday = findViewById(R.id.showBirthday)
        alert_gender = findViewById(R.id.alert_gender)
        alert_birthday = findViewById(R.id.alert_birthday)
//        alert_relationship = findViewById(R.id.alert_relationship)
//        alert_disease = findViewById(R.id.alert_disease)
        disease1 = findViewById(R.id.disease1)
        disease2 = findViewById(R.id.disease2)
        disease3 = findViewById(R.id.disease3)
        disease4 = findViewById(R.id.disease4)

        btn_back.setOnClickListener{
            finish()
            super.onBackPressed()
        }

        showBirthday.text = "$daySelect/$monthSelect/$yearSelect"
        alert_birthday.visibility = View.VISIBLE


        btn_next.setOnClickListener{

            if(getGender().equals("no gender")) goToNext = false

            when(goToNext){
                true -> startActivity(Intent(applicationContext,ProfileThirdActivity::class.java))
                false -> return@setOnClickListener
            }


        }



        birthday.setOnClickListener {

            var datePicker = DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

                if(dayOfMonth >= getInstance().get(Calendar.DAY_OF_MONTH) && month+1 >= getInstance().get(Calendar.MONTH)+1 && year >= getInstance().get(Calendar.YEAR)){
                    alert_birthday.visibility = View.VISIBLE
                    goToNext = false
                }else{
                    alert_birthday.visibility = View.GONE
                    goToNext = true
                }

                daySelect = dayOfMonth
                monthSelect = month+1
                yearSelect = year

                showBirthday.text = "$daySelect/$monthSelect/$yearSelect"


            },yearSelect,monthSelect-1,daySelect)

            datePicker.show()

        }


        setAdapterDisease(disease1)
        setAdapterDisease(disease2)
        setAdapterDisease(disease3)
        setAdapterDisease(disease4)

    }

    private fun getGender() : String{

        val selectIDGender = findViewById<RadioGroup>(R.id.gender).checkedRadioButtonId

        return if(selectIDGender == -1){
            alert_gender.visibility = View.VISIBLE
            "no gender"
        }else{
            alert_gender.visibility = View.GONE
            val selectChoiceGender = findViewById<RadioButton>(selectIDGender)
            selectChoiceGender.text.toString()
        }
    }

//    private fun getMaritalStatus():String{
//
//        val selectIDStatus = findViewById<RadioGroup>(R.id.relationship).checkedRadioButtonId
//
//        return if(selectIDStatus == -1){
//            alert_relationship.visibility = View.VISIBLE
//            "no select status"
//        }else{
//            alert_relationship.visibility = View.GONE
//            val selectChoiceStatus = findViewById<RadioButton>(selectIDStatus)
//            selectChoiceStatus.text.toString()
//        }
//
//    }


//    private fun getDisease():String {
//
//        val selectIDDisease = findViewById<RadioGroup>(R.id.disease).checkedRadioButtonId
//
//        return if(selectIDDisease == -1){
//            alert_disease.visibility = View.VISIBLE
//            "no select Disease"
//        }else{
//            alert_disease.visibility = View.GONE
//            val selectChoiceDisease = findViewById<RadioButton>(selectIDDisease)
//            selectChoiceDisease.text.toString()
//        }
//
//
//    }



    private fun setAdapterDisease(disease:Spinner) {

        val diseaseArray = resources.getStringArray(R.array.Disease)
        val adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, diseaseArray)
        disease.adapter = adapter

        disease.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View, position: Int, id: Long) {
                val tag = disease.tag
                when {
                    tag.equals("dis1") -> dis1 = diseaseArray[position].toString()
                    tag.equals("dis2") -> dis2 = diseaseArray[position].toString()
                    tag.equals("dis3") -> dis3 = diseaseArray[position].toString()
                    tag.equals("dis4") -> dis4 = diseaseArray[position].toString()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }


}