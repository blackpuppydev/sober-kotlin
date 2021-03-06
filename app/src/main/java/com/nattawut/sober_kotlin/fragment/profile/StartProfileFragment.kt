package com.nattawut.sober_kotlin.fragment.profile

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import com.nattawut.sober_kotlin.R
import com.nattawut.sober_kotlin.constance.LandingPage
import com.nattawut.sober_kotlin.constance.TypeData
import com.nattawut.sober_kotlin.listener.FragmentEvent
import com.nattawut.sober_kotlin.manager.DBManager
import java.lang.ClassCastException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val NAME = "name"
private const val LNAME = "lname"

/**
 * A simple [Fragment] subclass.
 * Use the [StartProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StartProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var ed_name: EditText
    private lateinit var alertText: TextView
    private lateinit var btn_confirm: RelativeLayout

    private lateinit var listener:FragmentEvent

    private var firstname:String? = getString(R.string.firstname)
    private var lastname:String? = getString(R.string.lastname)

    private var dbManager: DBManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            firstname = it.getString(NAME)
            lastname = it.getString(LNAME)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as FragmentEvent
        }catch (e:ClassCastException){}
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val v:View = inflater.inflate(R.layout.fragment_start_profile, container, false)
        ed_name = v.findViewById(R.id.ed_name)
        alertText = v.findViewById(R.id.alert)
        btn_confirm = v.findViewById(R.id.btn_confirm)

        ed_name.setText("$firstname $lastname")

        dbManager = DBManager(context!!)

        btn_confirm.setOnClickListener{

            val text = ed_name.text.toString()

            if(text.isEmpty()|| text == "") {
                alertText.text = R.string.test_taker.toString()
            }else if(checkData()){
                listener.onSuccess(LandingPage.HAVE_PROFILE)
            }else{
                //split string
                getSplitName(text)
                listener.onResult(firstname!!,TypeData.PSN_NAME)
                listener.onResult(lastname!!,TypeData.PSN_LNAME)
                listener.onSuccess(LandingPage.ADD_PROFILE)
            }
        }



        return v
    }


    private fun checkData():Boolean{ //getTextEmp
//        if(dbManager?.getTextAdmin(firstname.toString(),TypeData.FIRSTNAME) == firstname &&
//            dbManager?.getTextAdmin(lastname.toString(),TypeData.FIRSTNAME) == lastname){
//            return true
//        }
        return false
    }

    private fun getSplitName(name: String){ //List<String>
        val text = name.split(" ")
        if (text.size == 1){
           firstname = text[0]
           return
        }

        if(text.size > 2){
            firstname = "${text[0]} ${text[1]}"
            lastname = text[2]
        }else{
            firstname = text[0]
            lastname = text[1]
        }
    }




    companion object {

        @JvmStatic
        fun newInstance(firstname: String, lastname: String) =
            StartProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(NAME, firstname)
                    putString(LNAME,lastname)
                }
            }
    }
}