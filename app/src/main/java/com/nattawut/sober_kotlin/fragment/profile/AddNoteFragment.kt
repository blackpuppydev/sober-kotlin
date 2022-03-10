package com.nattawut.sober_kotlin.fragment.profile

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import com.nattawut.sober_kotlin.R
import com.nattawut.sober_kotlin.constance.LandingPage
import com.nattawut.sober_kotlin.constance.TypeData
import com.nattawut.sober_kotlin.listener.FragmentEvent
import java.lang.ClassCastException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddNoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddNoteFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var note: String? = null
    private var param2: String? = null

    private lateinit var addNote:EditText
    private lateinit var btn_confirm:RelativeLayout
    lateinit var listener: FragmentEvent

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as FragmentEvent
        }catch (e: ClassCastException){}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            note = it.getString(NOTE)
            param2 = it.getString(ARG_PARAM2)
            Toast.makeText(context,"AddNote : $note",Toast.LENGTH_SHORT).show()
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        val v:View = inflater.inflate(R.layout.fragment_add_note, container, false)
        addNote = v.findViewById(R.id.addNote)
        btn_confirm = v.findViewById(R.id.btn_confirm)


        addNote.setText(note)

        btn_confirm.setOnClickListener {
            listener.onResult(addNote.text.toString(),TypeData.PSN_NOTE)
            listener.onSuccess(LandingPage.BACK)
        }

        return v
    }

    companion object {

        const val NOTE = "note"

        @JvmStatic
        fun newInstance(note: String, param2: String) =
            AddNoteFragment().apply {
                arguments = Bundle().apply {
                    putString(NOTE, note)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}