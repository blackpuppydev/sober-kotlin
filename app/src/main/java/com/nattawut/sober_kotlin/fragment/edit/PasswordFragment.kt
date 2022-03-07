package com.nattawut.sober_kotlin.fragment.edit

import android.content.Context
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.nattawut.sober_kotlin.AppPreference
import com.nattawut.sober_kotlin.R
import com.nattawut.sober_kotlin.constance.LandingPage
import com.nattawut.sober_kotlin.constance.TypeData
import com.nattawut.sober_kotlin.listener.FragmentEvent
import com.nattawut.sober_kotlin.manager.DBManager
import kotlinx.android.synthetic.main.activity_login.*
import java.lang.ClassCastException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PasswordFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PasswordFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var btn_back:LinearLayout
    private lateinit var btn_oldHide:ImageButton
    private lateinit var btn_newHide:ImageButton
    private lateinit var btn_conNewHide:ImageButton
    private lateinit var oldPass:EditText
    private lateinit var newPass:EditText
    private lateinit var conNewPass:EditText
    private lateinit var btn_confirm:RelativeLayout
    private lateinit var listener: FragmentEvent

    private var dbManager: DBManager? = null

    private var hideOld : Boolean = false
    private var hideNew : Boolean = false
    private var hideCon : Boolean = false


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
    ): View {
        // Inflate the layout for this fragment
        val v:View = inflater.inflate(R.layout.fragment_password, container, false)

        btn_back = v.findViewById(R.id.btn_back)
        btn_oldHide = v.findViewById(R.id.btn_oldHide)
        btn_newHide = v.findViewById(R.id.btn_newHide)
        btn_conNewHide = v.findViewById(R.id.btn_newHideCon)
        oldPass = v.findViewById(R.id.old_password)
        newPass = v.findViewById(R.id.new_password)
        conNewPass = v.findViewById(R.id.con_new_password)
        btn_confirm = v.findViewById(R.id.btn_confirm)

        dbManager = DBManager(context!!)
        AppPreference.getInstance().setSharedPreference(context!!)

        btn_back.setOnClickListener {
            listener.onSuccess(LandingPage.BACK)
        }

        btn_oldHide.setOnClickListener {
            if(hideOld){
                oldPass.transformationMethod = HideReturnsTransformationMethod.getInstance()
                hideOld = false
                btn_oldHide.setBackgroundResource(R.drawable.eye_close)
            } else{
                oldPass.transformationMethod = PasswordTransformationMethod.getInstance()
                hideOld = true
                btn_oldHide.setBackgroundResource(R.drawable.eye_open)
            }
        }

        btn_newHide.setOnClickListener {
            if(hideNew){
                newPass.transformationMethod = HideReturnsTransformationMethod.getInstance()
                hideNew = false
                btn_newHide.setBackgroundResource(R.drawable.eye_close)
            } else{
                newPass.transformationMethod = PasswordTransformationMethod.getInstance()
                hideNew = true
                btn_newHide.setBackgroundResource(R.drawable.eye_open)
            }
        }

        btn_conNewHide.setOnClickListener {
            if(hideCon){
                conNewPass.transformationMethod = HideReturnsTransformationMethod.getInstance()
                hideCon = false
                btn_conNewHide.setBackgroundResource(R.drawable.eye_close)
            } else{
                conNewPass.transformationMethod = PasswordTransformationMethod.getInstance()
                hideCon = true
                btn_conNewHide.setBackgroundResource(R.drawable.eye_open)
            }
        }

        btn_confirm.setOnClickListener {
            if (oldPass.text.toString() != "" && newPass.text.toString() != "" && conNewPass.text.toString() != "" ){
                if (oldPass.text.toString() == AppPreference.getInstance().getPassword()){
                    if(newPass.text.toString() == conNewPass.text.toString()){ //update password
                        dbManager?.updatePassword(conNewPass.text.toString(),AppPreference.getInstance().getUsername().toString())
                        AppPreference.getInstance().setPassword(conNewPass.text.toString())
                    }else Toast.makeText(context,R.string.same_pass, Toast.LENGTH_SHORT).show()
                }else Toast.makeText(context,R.string.old_invalid, Toast.LENGTH_SHORT).show()
            }else Toast.makeText(context,R.string.infor_com, Toast.LENGTH_SHORT).show()
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
         * @return A new instance of fragment PasswordFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PasswordFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}