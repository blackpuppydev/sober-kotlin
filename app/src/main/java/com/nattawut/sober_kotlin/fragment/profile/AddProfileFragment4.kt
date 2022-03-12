package com.nattawut.sober_kotlin.fragment.profile

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nattawut.sober_kotlin.R
import com.nattawut.sober_kotlin.listener.FragmentEvent
import com.nattawut.sober_kotlin.model.MenuDetail
import com.nattawut.sober_kotlin.model.MenuOther
import java.lang.ClassCastException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val NAME = "name"
private const val GENDER = "gender"
private const val OLD = "old"
private const val BLOOD = "blood"
private const val STATUS = "status"
private const val NATION = "nation"
private const val ADDRESS = "address"
private const val EDU_LV = "edu_level"
private const val OCCUPATION = "occupation"
private const val DISEASE = "disease"
private const val NOTE = "note"
private const val PIC = "pic"
//private const val ARG_PARAM2 = "param2"
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddProfileFragment4.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddProfileFragment4 : Fragment() {
    // TODO: Rename and change types of parameters
    private var name: String? = null
    private var gender: String? = null
    private var old: String? = null
    private var blood: String? = null
    private var status: String? = null
    private var nation: String? = null
    private var address: String? = null
    private var edu_level: String? = null
    private var occupation: String? = null
    private var disease: String? = null
    private var note: String? = null
    private var pic: Bitmap? = null

    private lateinit var list: RecyclerView
    private lateinit var listener: FragmentEvent
    lateinit var item : ArrayList<MenuDetail>


    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as FragmentEvent
        }catch (e: ClassCastException){}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString(NAME)
            gender = it.getString(GENDER)
            old = it.getString(OLD)
            blood = it.getString(BLOOD)
            status = it.getString(STATUS)
            nation = it.getString(NATION)
            address = it.getString(ADDRESS)
            edu_level = it.getString(EDU_LV)
            occupation = it.getString(OCCUPATION)
            disease = it.getString(DISEASE)
            note = it.getString(NOTE)
            pic = it.getParcelable(PIC)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val v:View = inflater.inflate(R.layout.fragment_add_profile4, container, false)

        list = v.findViewById(R.id.list)

        addData()


        var lm = GridLayoutManager(context!!,1)
        lm.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return 1
            }
        }
        list.layoutManager = lm

        return v
    }



    private fun addData(){
        item = ArrayList()
        item.add(MenuDetail(getString(R.string.status),""))
        item.add(MenuDetail(getString(R.string.nation),""))
        item.add(MenuDetail(getString(R.string.address),""))
        item.add(MenuDetail(getString(R.string.edu_level),""))
        item.add(MenuDetail(getString(R.string.occa_pos),""))
        item.add(MenuDetail(getString(R.string.disease),""))
        item.add(MenuDetail(getString(R.string.note),""))
        item.add(MenuDetail(getString(R.string.pic),""))
    }


    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(name: String, gender: String, old: String, blood: String, status: String, nation: String, address: String,
                        edu_level: String, occupation: String, disease: String, note: String, pic: Bitmap) =
            AddProfileFragment4().apply {
                arguments = Bundle().apply {
                    putString(NAME, name)
                    putString(GENDER, gender)
                    putString(OLD, old)
                    putString(BLOOD, blood)
                    putString(STATUS, status)
                    putString(NATION, nation)
                    putString(ADDRESS, address)
                    putString(EDU_LV, edu_level)
                    putString(OCCUPATION, occupation)
                    putString(DISEASE, disease)
                    putString(NOTE, note)
                    putParcelable(PIC, pic)
                }
            }
    }
}