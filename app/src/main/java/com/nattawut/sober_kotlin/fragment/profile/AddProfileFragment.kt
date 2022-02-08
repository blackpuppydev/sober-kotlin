package com.nattawut.sober_kotlin.fragment.profile

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.nattawut.sober_kotlin.R
import com.nattawut.sober_kotlin.activity.ProfileThirdActivity
import com.nattawut.sober_kotlin.constance.LandingPage
import com.nattawut.sober_kotlin.constance.TypeData
import com.nattawut.sober_kotlin.listener.ProfileEvent
import java.lang.ClassCastException
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddProfileFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var gender:String? = null
    private var birthDay:String? = null
    lateinit var listener:ProfileEvent

    private lateinit var sl_male:RelativeLayout
    private lateinit var sl_female:RelativeLayout
    private lateinit var sl_other_gender:RelativeLayout
    private lateinit var sl_no_entry:RelativeLayout
    private lateinit var getBirthday:LinearLayout
    private lateinit var showBirthday:TextView
    private lateinit var alert_birthday:TextView
    private lateinit var alert_gender:TextView
    private lateinit var btn_confirm:RelativeLayout
    private lateinit var btn_back_fm:ImageButton

    //values choice
    //Calendar
    var calendar:Calendar = Calendar.getInstance()
    var daySelect:Int = calendar.get(Calendar.DAY_OF_MONTH)
    var monthSelect :Int = calendar.get(Calendar.MONTH)+1
    var yearSelect:Int = calendar.get(Calendar.YEAR)

    var goToNext = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            listener = context as ProfileEvent
        }catch (e:ClassCastException){}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v:View = inflater.inflate(R.layout.fragment_add_profile, container, false)

        //init view
        sl_male = v.findViewById(R.id.sl_male)
        sl_female = v.findViewById(R.id.sl_female)
        sl_other_gender = v.findViewById(R.id.sl_other_gender)
        sl_no_entry = v.findViewById(R.id.sl_no_entry)
        getBirthday = v.findViewById(R.id.getBirthday)
        showBirthday = v.findViewById(R.id.showBirthday)
        alert_gender = v.findViewById(R.id.alert_gender)
        alert_birthday = v.findViewById(R.id.alert_birthday)
        btn_confirm = v.findViewById(R.id.btn_confirm)
        btn_back_fm = v.findViewById(R.id.btn_back_fm)

        btn_confirm.setOnClickListener {

            if(gender == null){
                alert_gender.visibility = View.VISIBLE
                goToNext = false
            }else{
                alert_gender.visibility = View.GONE
                goToNext = true
            }




            when(goToNext){
                true -> {
                    listener.onSuccess(LandingPage.ADD_PROFILE2)
                    listener.onResult(gender.toString(),TypeData.GENDER)
                    listener.onResult(showBirthday.text.toString(),TypeData.BIRTHDAY)
                    //disease
                }
                false -> return@setOnClickListener
            }
        }

        btn_back_fm.setOnClickListener {
            listener.onSuccess(LandingPage.BACK)
        }

        sl_male.setOnClickListener {
            gender = "male"
            sl_male.setBackgroundResource(R.drawable.selector_gender2)
            sl_female.setBackgroundResource(R.drawable.selector_gender)
            sl_other_gender.setBackgroundResource(R.drawable.selector_gender)
            sl_no_entry.setBackgroundResource(R.drawable.selector_gender)
        }
        sl_female.setOnClickListener {
            gender = "female"
            sl_male.setBackgroundResource(R.drawable.selector_gender)
            sl_female.setBackgroundResource(R.drawable.selector_gender2)
            sl_other_gender.setBackgroundResource(R.drawable.selector_gender)
            sl_no_entry.setBackgroundResource(R.drawable.selector_gender)
        }
        sl_other_gender.setOnClickListener {
            gender = "other"
            sl_male.setBackgroundResource(R.drawable.selector_gender)
            sl_female.setBackgroundResource(R.drawable.selector_gender)
            sl_other_gender.setBackgroundResource(R.drawable.selector_gender2)
            sl_no_entry.setBackgroundResource(R.drawable.selector_gender)
        }
        sl_no_entry.setOnClickListener {
            gender = "no"
            sl_male.setBackgroundResource(R.drawable.selector_gender)
            sl_female.setBackgroundResource(R.drawable.selector_gender)
            sl_other_gender.setBackgroundResource(R.drawable.selector_gender)
            sl_no_entry.setBackgroundResource(R.drawable.selector_gender2)
        }

        getBirthday.setOnClickListener {

            var datePicker = DatePickerDialog(context!!, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

                if(dayOfMonth >= Calendar.getInstance()
                        .get(Calendar.DAY_OF_MONTH) && month+1 >= Calendar.getInstance()
                        .get(Calendar.MONTH)+1 && year >= Calendar.getInstance().get(Calendar.YEAR)){
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







        return v

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}