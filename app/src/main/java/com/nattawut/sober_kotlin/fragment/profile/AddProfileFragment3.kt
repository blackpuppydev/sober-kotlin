package com.nattawut.sober_kotlin.fragment.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nattawut.sober_kotlin.R
import com.nattawut.sober_kotlin.activity.QuizActivity
import com.nattawut.sober_kotlin.adapter.ItemOtherAdapter
import com.nattawut.sober_kotlin.adapter.TypeTestAdapter
import com.nattawut.sober_kotlin.model.MenuOther

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
//                startActivity(
//                    Intent(context!!, QuizActivity::class.java)
//                    .putExtra("type", position.toString()))

//                startActivity(Intent(applicationContext,QuestionActivity::class.java)
//                    .putExtra("header","ชุดคำถามที่ ${position+1}"))
            }
        }
        list.adapter = adapter




        return v
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