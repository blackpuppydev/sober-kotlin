package com.nattawut.sober_kotlin.fragment.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nattawut.sober_kotlin.R
import com.nattawut.sober_kotlin.activity.QuizActivity
import com.nattawut.sober_kotlin.adapter.ItemOtherAdapter
import com.nattawut.sober_kotlin.adapter.TypeTestAdapter
import com.nattawut.sober_kotlin.constance.LandingPage
import com.nattawut.sober_kotlin.constance.TypeData
import com.nattawut.sober_kotlin.listener.FragmentEvent
import com.nattawut.sober_kotlin.model.MenuOther
import java.lang.ClassCastException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddProfileFragment3.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddProfileFragment3 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var list:RecyclerView
    lateinit var item : ArrayList<MenuOther>
    private lateinit var btn_confirm:RelativeLayout
    private lateinit var alert_disease:TextView
    private lateinit var diseaseAdd:EditText
    lateinit var listener: FragmentEvent

    var goToNext = true

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as FragmentEvent
        }catch (e: ClassCastException){}
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
    ): View? {
        // Inflate the layout for this fragment
        val  v =  inflater.inflate(R.layout.fragment_add_profile3, container, false)

        list = v.findViewById(R.id.list)
        btn_confirm = v.findViewById(R.id.btn_confirm)
        alert_disease = v.findViewById(R.id.alert_disease)
        diseaseAdd = v.findViewById(R.id.diseaseAdd)

        item = ArrayList<MenuOther>()
        item.add(MenuOther(R.drawable.note,R.string.note.toString()))
        item.add(MenuOther(R.drawable.camera,R.string.pic.toString()))

        var lm = GridLayoutManager(context!!,2)
        lm.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return 1
            }
        }
        list.layoutManager = lm

        val adapter = object : ItemOtherAdapter(item){
            override fun onSuccess(position: Int) {

                Toast.makeText(context!!,"position : $position",Toast.LENGTH_SHORT).show()

                if(position == 0){
                    val fragment = AddNoteFragment()
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.fragment_profile,fragment,LandingPage.ADD_NOTE)
                        ?.addToBackStack(LandingPage.ADD_NOTE)
                        ?.commit()
                }else if(position == 1){
                    val fragment = AddPhotoFragment()
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.fragment_profile,fragment,LandingPage.ADD_PIC)
                        ?.addToBackStack(LandingPage.ADD_PIC)
                        ?.commit()
                }
            }
        }
        list.adapter = adapter


        btn_confirm.setOnClickListener {

            when(goToNext){
                true -> {
                    if(getDisease(v) == R.string.have.toString() && diseaseAdd.text.toString() != ""){
                        listener.onResult(diseaseAdd.text.toString(),TypeData.PSN_CONGENITAL_DIS)
                        listener.onSuccess(LandingPage.ADD_PROFILE4)
                    }else if(getDisease(v) == R.string.have_not.toString()){
                        listener.onSuccess(LandingPage.ADD_PROFILE4)
                    }
                }
                false -> {
                    return@setOnClickListener
                }
            }

        }



        return v
    }


    private fun getDisease(v:View):String{

        val selectDisease = v.findViewById<RadioGroup>(R.id.disease).checkedRadioButtonId

        return if(selectDisease == -1){
            alert_disease.visibility = View.VISIBLE
            goToNext = false
            "none"
        }else{
            alert_disease.visibility = View.GONE
            goToNext = true
            val selectChoiceDisease = v.findViewById<RadioButton>(selectDisease)
            selectChoiceDisease.text.toString()
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddProfileFragment3.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddProfileFragment3().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}