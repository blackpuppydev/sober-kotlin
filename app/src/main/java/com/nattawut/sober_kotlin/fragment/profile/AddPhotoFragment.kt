package com.nattawut.sober_kotlin.fragment.profile

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.drawToBitmap
import com.nattawut.sober_kotlin.R
import com.nattawut.sober_kotlin.constance.LandingPage
import com.nattawut.sober_kotlin.constance.TypeData
import com.nattawut.sober_kotlin.listener.FragmentEvent
import java.lang.ClassCastException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val PIC = "pic"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddPhotoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddPhotoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var pic:ImageView
    private lateinit var dialog: Dialog
    private lateinit var btn_confirm:RelativeLayout
    private lateinit var listener: FragmentEvent


    var CAMERA_REQUEST_CODE = 107
    var GALLERY_REQUEST_CODE = 108

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            listener = context as FragmentEvent
        }catch (e: ClassCastException){}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(PIC)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v:View =  inflater.inflate(R.layout.fragment_add_photo, container, false)

        pic = v.findViewById(R.id.pic)
        btn_confirm = v.findViewById(R.id.btn_confirm)


        pic.setOnClickListener {
            dialog = Dialog(context!!)
            dialog.setContentView(R.layout.dialog_select_picture)

            val camera: RelativeLayout = dialog.findViewById(R.id.camera)
            val gallery: RelativeLayout = dialog.findViewById(R.id.gallery)

            camera.setOnClickListener { openCamera() }
            gallery.setOnClickListener { openGallery() }
            dialog.show()
        }


        btn_confirm.setOnClickListener {
            listener.onResult(pic.drawToBitmap(),TypeData.PSN_PIC)
            listener.onSuccess(LandingPage.BACK)
        }

        return v
    }



    private fun openCamera() {
        val camera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(camera,CAMERA_REQUEST_CODE)
    }

    private fun openGallery(){
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(gallery,GALLERY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CAMERA_REQUEST_CODE){
            val image : Bitmap = data?.extras?.get("data") as Bitmap
            pic.clearColorFilter()
            pic.setImageBitmap(image)
            dialog.dismiss()
        }else if(requestCode == GALLERY_REQUEST_CODE){
            var selectedImage: Uri = data?.data as Uri
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
            val cursor: Cursor? = context?.contentResolver?.query(
                selectedImage,
                filePathColumn, null, null, null
            )
            if (cursor != null) {
                cursor.moveToFirst()
                val columnIndex: Int = cursor.getColumnIndex(filePathColumn[0])
                val picturePath: String = cursor.getString(columnIndex)
                pic.clearColorFilter()
                pic.setImageBitmap(BitmapFactory.decodeFile(picturePath))
                cursor.close()
                dialog.dismiss()
            }
        }
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddPhotoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(pic: Bitmap, param2: String) =
            AddPhotoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(PIC, pic)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}