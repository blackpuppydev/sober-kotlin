package com.nattawut.sober_kotlin.fragment.profile

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.nattawut.sober_kotlin.R
import com.nattawut.sober_kotlin.constance.LandingPage
import com.nattawut.sober_kotlin.constance.TypeData
import com.nattawut.sober_kotlin.listener.FragmentEvent
import kotlinx.android.synthetic.main.activity_profile_third.*
import java.lang.ClassCastException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AddProfileFragment2 : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var alert_relationship: TextView
    private lateinit var alert_address:TextView
    private lateinit var alert_edu:TextView
    private lateinit var alert_occupation:TextView
    private lateinit var addAddress:EditText
    private lateinit var addOccupation:EditText
    private lateinit var btn_back_fm:ImageButton
    private lateinit var btn_confirm:RelativeLayout
    private lateinit var addEdu:Spinner
    lateinit var listener: FragmentEvent

    var goToNext = true
    var edu_text = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as FragmentEvent
        }catch (e:ClassCastException){}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        var v:View = inflater.inflate(R.layout.fragment_add_profile2, container, false)

        btn_back_fm = v.findViewById(R.id.btn_back_fm2)
        btn_confirm = v.findViewById(R.id.btn_confirm)
        alert_relationship = v.findViewById(R.id.alert_relationship)
        alert_address = v.findViewById(R.id.alert_address)
        alert_edu = v.findViewById(R.id.alert_edu)
        alert_occupation = v.findViewById(R.id.alert_occupation)
        addAddress = v.findViewById(R.id.addressAdd)
        addEdu = v.findViewById(R.id.edu)
        addOccupation = v.findViewById(R.id.occupationAdd)

        setAdapterEdu(addEdu)


        btn_back_fm.setOnClickListener {
            listener.onSuccess(LandingPage.BACK)
        }


        btn_confirm.setOnClickListener {

            if (addAddress.text.toString() == "") {
                alert_address.visibility = View.VISIBLE
                goToNext = false
                return@setOnClickListener
            } else alert_address.visibility = View.GONE


            if (addOccupation.text.toString() == "") {
                alert_occupation.visibility = View.VISIBLE
                goToNext = false
                return@setOnClickListener
            } else alert_occupation.visibility = View.GONE


            when(goToNext){
                true -> {
                    listener.onResult(getRelationship(v),TypeData.PSN_STATUS)
                    listener.onResult(addAddress.text.toString(),TypeData.PSN_ADDRESS)
                    listener.onResult(edu_text,TypeData.PSN_EDU_LV)
                    listener.onResult(addOccupation.text.toString(),TypeData.PSN_CAREER)
                    listener.onSuccess(LandingPage.ADD_PROFILE3)
                }
                false -> {
                    return@setOnClickListener
                }
            }


        }

        return v
    }


    private fun getRelationship(v:View):String{

        val selectRelationship = v.findViewById<RadioGroup>(R.id.relationship).checkedRadioButtonId

        return if(selectRelationship == -1){
            alert_relationship.visibility = View.VISIBLE
            goToNext = false
            "none"
        }else{
            alert_relationship.visibility = View.GONE
            goToNext = true
            val selectChoiceRelationship = v.findViewById<RadioButton>(selectRelationship)
            selectChoiceRelationship.text.toString()
        }

    }


    private fun setAdapterEdu(education: Spinner) {

        val eduArray = resources.getStringArray(R.array.Edu_level)
        val adapter = ArrayAdapter(context!!,
            android.R.layout.simple_spinner_item, eduArray)
        education.adapter = adapter

        education.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                edu_text = eduArray[position].toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddProfileFragment2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddProfileFragment2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}