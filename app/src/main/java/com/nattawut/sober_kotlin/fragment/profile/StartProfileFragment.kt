package com.nattawut.sober_kotlin.fragment.profile

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import com.nattawut.sober_kotlin.R
import com.nattawut.sober_kotlin.constance.LandingPage
import com.nattawut.sober_kotlin.constance.TypeData
import com.nattawut.sober_kotlin.listener.FragmentEvent
import com.nattawut.sober_kotlin.manager.DBManager
import java.lang.ClassCastException
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StartProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StartProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var ed_name: EditText
    lateinit var alertText: TextView
    lateinit var btn_confirm: RelativeLayout

    lateinit var listener:FragmentEvent

    private var dbManager: DBManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as FragmentEvent
        }catch (e:ClassCastException){}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        var v:View = inflater.inflate(R.layout.fragment_start_profile, container, false)
        ed_name = v.findViewById(R.id.ed_name)
        alertText = v.findViewById(R.id.alert)
        btn_confirm = v.findViewById(R.id.btn_confirm)

        dbManager = DBManager(context!!)


        btn_confirm.setOnClickListener{

            val text = ed_name.text.toString()

            if(text.isEmpty()|| text == "") {
                alertText.text = R.string.test_taker.toString()
            }else if(checkData(text)){
                listener.onSuccess(LandingPage.HAVE_PROFILE)
            }else{
//                startActivity(Intent(applicationContext, ProfileSecondActivity::class.java).putExtra("name",text))
                listener.onSuccess(LandingPage.ADD_PROFILE)
            }
        }



        return v
    }


    private fun checkData(text:String):Boolean{
        //for test
//        if(dbManager?.getTextAdmin(ed_name.text.toString(),TypeData.FIRSTNAME) == ed_name.text.toString()){
//
//        }

        return text == "test"
    }




    companion object {



        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StartProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StartProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}