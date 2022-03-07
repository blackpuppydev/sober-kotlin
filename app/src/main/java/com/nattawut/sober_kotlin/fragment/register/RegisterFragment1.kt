package com.nattawut.sober_kotlin.fragment.register

import android.content.Context
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.Toast
import com.nattawut.sober_kotlin.R
import com.nattawut.sober_kotlin.constance.LandingPage
import com.nattawut.sober_kotlin.constance.TypeData
import com.nattawut.sober_kotlin.listener.FragmentEvent
import kotlinx.android.synthetic.main.activity_login.*
import java.lang.ClassCastException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment1.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment1 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var hidePass:Boolean = false
    private var hidePassCon:Boolean = false

    private lateinit var btn_back:ImageButton
    private lateinit var btn_next:RelativeLayout
    private lateinit var getUsername:EditText
    private lateinit var getMail:EditText
    private lateinit var getPassword:EditText
    private lateinit var getConPassword:EditText
    private lateinit var btn_hide:ImageButton
    private lateinit var btn_hideCon:ImageButton
    private lateinit var listener:FragmentEvent


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
        // Inflate the layout for this fragment
        var v:View = inflater.inflate(R.layout.fragment_register1, container, false)

        btn_next = v.findViewById(R.id.btn_next)
        btn_back = v.findViewById(R.id.btn_back)
        btn_hideCon = v.findViewById(R.id.btn_hideCon)
        btn_hide = v.findViewById(R.id.btn_hide)
        getUsername = v.findViewById(R.id.getUsername)
        getMail = v.findViewById(R.id.getMail)
        getPassword = v.findViewById(R.id.getPassword)
        getConPassword = v.findViewById(R.id.getConPassword)

        btn_back.setOnClickListener {
            listener.onSuccess(LandingPage.BACK)
        }

        btn_next.setOnClickListener {
            if (getUsername.text.toString() != "" && getMail.text.toString() != "" && getPassword.text.toString() != "" && getConPassword.text.toString() != ""){
                if(getPassword.text.toString() == getConPassword.text.toString()){
                    listener.onResult(getUsername.text.toString(),TypeData.USERNAME)
                    listener.onResult(getMail.text.toString(),TypeData.MAIL)
                    listener.onResult(getPassword.text.toString(),TypeData.PASSWORD)
                    listener.onSuccess(LandingPage.REGISTER2)
                }else Toast.makeText(context,R.string.same_pass,Toast.LENGTH_SHORT).show()
            }else Toast.makeText(context,R.string.infor_com,Toast.LENGTH_SHORT).show()
        }

        btn_hide.setOnClickListener {
            if(hidePass){
                getPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                hidePass = false
                btn_hide.setBackgroundResource(R.drawable.eye_close)
            } else{
                getPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                hidePass = true
                btn_hide.setBackgroundResource(R.drawable.eye_open)
            }
        }

        btn_hideCon.setOnClickListener {
            if(hidePassCon){
                getConPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                hidePassCon = false
                btn_hideCon.setBackgroundResource(R.drawable.eye_close)
            } else{
                getConPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                hidePassCon = true
                btn_hideCon.setBackgroundResource(R.drawable.eye_open)
            }
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
         * @return A new instance of fragment RegisterFragment1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterFragment1().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}