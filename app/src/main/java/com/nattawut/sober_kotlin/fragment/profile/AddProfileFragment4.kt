package com.nattawut.sober_kotlin.fragment.profile

import android.content.Context
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
private const val STATUS = "status"
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
    private var param1: String? = null
    private var param2: String? = null

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
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
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
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddProfileFragment4.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddProfileFragment4().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}