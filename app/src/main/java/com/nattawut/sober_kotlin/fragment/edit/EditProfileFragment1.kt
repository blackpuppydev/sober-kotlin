package com.nattawut.sober_kotlin.fragment.edit

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.nattawut.sober_kotlin.AppPreference
import com.nattawut.sober_kotlin.R
import com.nattawut.sober_kotlin.constance.LandingPage
import com.nattawut.sober_kotlin.constance.TypeData
import com.nattawut.sober_kotlin.listener.FragmentEvent
import com.nattawut.sober_kotlin.manager.DBManager
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ClassCastException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class EditProfileFragment1 : Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var dbManager: DBManager? = null
    private lateinit var listener:FragmentEvent
    private lateinit var pic:ImageView
    private lateinit var name: TextView
    private lateinit var fullName:TextView
    private lateinit var company:TextView
    private lateinit var position:TextView
    private lateinit var btn_edit:RelativeLayout
    private lateinit var btn_back:LinearLayout

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

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v:View =  inflater.inflate(R.layout.fragment_edit_profile1, container, false)

        pic = v.findViewById(R.id.pic)
        name = v.findViewById(R.id.name)
        fullName = v.findViewById(R.id.fullName)
        company = v.findViewById(R.id.company)
        position = v.findViewById(R.id.position)
        btn_edit = v.findViewById(R.id.btn_edit)
        btn_back = v.findViewById(R.id.btn_back)


        AppPreference.getInstance().setSharedPreference(context!!)
        dbManager = DBManager(context!!)


        val ob = BitmapDrawable(resources, dbManager!!.getPictureProfile(AppPreference.getInstance().getUsername().toString()))
        pic.background = ob

        name.text = "คุณ ${
            dbManager?.getTextAdmin(
                AppPreference.getInstance().getUsername().toString(),
                TypeData.FIRSTNAME
            )
        }"
        fullName.text = "${
            dbManager?.getTextAdmin(
                AppPreference.getInstance().getUsername().toString(),
                TypeData.FIRSTNAME
            )
        } " +
                "${
                    dbManager?.getTextAdmin(
                        AppPreference.getInstance().getUsername().toString(),
                        TypeData.LASTNAME
                    )
                }"

        company.text = " : ${dbManager?.getTextAdmin(AppPreference.getInstance().getUsername().toString(),TypeData.COMPANY)}"
        position.text = " : ${dbManager?.getTextAdmin(AppPreference.getInstance().getUsername().toString(),TypeData.VACATION)}"

        btn_back.setOnClickListener {
            listener.onSuccess(LandingPage.BACK)
        }

        btn_edit.setOnClickListener {
            listener.onSuccess(LandingPage.EDIT_PROFILE2)
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
         * @return A new instance of fragment EditProfileFragment1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EditProfileFragment1().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}