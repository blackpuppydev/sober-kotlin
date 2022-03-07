package com.nattawut.sober_kotlin.fragment.register

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
import java.lang.ClassCastException
import android.widget.ArrayAdapter
import com.nattawut.sober_kotlin.manager.DBManager


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment2 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var getName:EditText
    private lateinit var getLname:EditText
    private lateinit var getCompany:AutoCompleteTextView
    private lateinit var getVacation:AutoCompleteTextView
    private lateinit var btn_back:ImageButton
    private lateinit var btn_confirm:RelativeLayout
    private lateinit var listener: FragmentEvent

    private var dbManager: DBManager? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
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
        var v: View =  inflater.inflate(R.layout.fragment_register2, container, false)

        getName = v.findViewById(R.id.getName)
        getLname = v.findViewById(R.id.getLname)
        getCompany = v.findViewById(R.id.getCompany)
        getVacation = v.findViewById(R.id.getVacation)
        btn_back = v.findViewById(R.id.btn_back)
        btn_confirm = v.findViewById(R.id.btn_confirm)

        dbManager = DBManager(context!!)

        setAdapterCompany()
        setAdapterVacation()

        btn_back.setOnClickListener {
            listener.onSuccess(LandingPage.BACK)
        }

        btn_confirm.setOnClickListener {
            if (getName.text.toString() != "" && getLname.text.toString() != "" && getCompany.text.toString() != "" && getVacation.text.toString() != ""){
                listener.onResult(getName.text.toString(), TypeData.FIRSTNAME)
                listener.onResult(getLname.text.toString(), TypeData.LASTNAME)
                listener.onResult(getCompany.text.toString(), TypeData.COMPANY)
                listener.onResult(getVacation.text.toString(), TypeData.VACATION)
                listener.onSuccess(LandingPage.LOGIN)
            }else Toast.makeText(context,R.string.infor_com, Toast.LENGTH_SHORT).show()
        }


        return v
    }


    private fun setAdapterCompany(){

        val company =
            arrayOf("Company1", "Sabuy", "Google", "Facebook", "Apple", "Android" ,"Company2")


        val adapter: ArrayAdapter<String>? =
            context?.let { ArrayAdapter<String>(it, android.R.layout.select_dialog_singlechoice, company) }

        getCompany.threshold = 1
        getCompany.setAdapter(adapter)

    }

    private fun setAdapterVacation(){

        val vacation =
            arrayOf("Programmer", "Police", "Engineer", "Artist", "Doctor", "Nurse")


        val adapter: ArrayAdapter<String>? =
            context?.let { ArrayAdapter<String>(it, android.R.layout.select_dialog_singlechoice, vacation) }

        getVacation.threshold = 1
        getVacation.setAdapter(adapter)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegisterFragment2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterFragment2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}