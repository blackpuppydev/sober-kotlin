package com.nattawut.sober_kotlin.fragment.edit

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.graphics.drawable.toBitmap
import com.nattawut.sober_kotlin.AppPreference
import com.nattawut.sober_kotlin.R
import com.nattawut.sober_kotlin.constance.LandingPage
import com.nattawut.sober_kotlin.constance.TypeData
import com.nattawut.sober_kotlin.listener.FragmentEvent
import com.nattawut.sober_kotlin.manager.DBManager
import java.lang.ClassCastException
import android.graphics.drawable.BitmapDrawable

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import androidx.core.view.drawToBitmap
import kotlinx.android.synthetic.main.fragment_start_profile.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EditProfileFragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditProfileFragment2 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var dbManager: DBManager? = null
    private lateinit var listener:FragmentEvent
    private lateinit var btn_back:LinearLayout
    private lateinit var btn_confirm:RelativeLayout
    private lateinit var pic: ImageView
    private lateinit var username:EditText
    private lateinit var firstname:EditText
    private lateinit var lastname:EditText
    private lateinit var company:EditText
    private lateinit var position:EditText
    private lateinit var mail:EditText
    private lateinit var dialog: Dialog
    private lateinit var title_com:TextView
    private lateinit var title_pos:TextView

    var CAMERA_REQUEST_CODE = 103
    var GALLERY_REQUEST_CODE = 104



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

        val v:View = inflater.inflate(R.layout.fragment_edit_profile2, container, false)

        btn_back = v.findViewById(R.id.btn_back)
        btn_confirm = v.findViewById(R.id.btn_confirm)
        pic = v.findViewById(R.id.pic)
        username = v.findViewById(R.id.username)
        firstname = v.findViewById(R.id.firstname)
        lastname = v.findViewById(R.id.lastname)
        company = v.findViewById(R.id.company)
        position = v.findViewById(R.id.position)
        mail = v.findViewById(R.id.mail)
        title_com = v.findViewById(R.id.title_com)
        title_pos = v.findViewById(R.id.title_pos)

        AppPreference.getInstance().setSharedPreference(context!!)
        dbManager = DBManager(context!!)

        val ob = BitmapDrawable(resources, dbManager!!.getPictureProfile(AppPreference.getInstance().getUsername().toString()))
        pic.background = ob

        username.setText("${dbManager?.getTextAdmin(AppPreference.getInstance().getUsername().toString(),TypeData.USERNAME)}")
        firstname.setText("${dbManager?.getTextAdmin(AppPreference.getInstance().getUsername().toString(),TypeData.FIRSTNAME)}")
        lastname.setText("${dbManager?.getTextAdmin(AppPreference.getInstance().getUsername().toString(),TypeData.LASTNAME)}")
        mail.setText("${dbManager?.getTextAdmin(AppPreference.getInstance().getUsername().toString(),TypeData.MAIL)}")
        company.setText("${dbManager?.getTextAdmin(AppPreference.getInstance().getUsername().toString(),TypeData.COMPANY)}")
        position.setText("${dbManager?.getTextAdmin(AppPreference.getInstance().getUsername().toString(),TypeData.VACATION)}")

        if(dbManager?.getTextAdmin(AppPreference.getInstance().getUsername().toString(),TypeData.COMPANY) == "master"){
            company.visibility = View.GONE
            position.visibility = View.GONE
            title_com.visibility = View.GONE
            title_pos.visibility = View.GONE
        }

        btn_back.setOnClickListener {
            listener.onSuccess(LandingPage.BACK)
        }

        pic.setOnClickListener {
            dialog = Dialog(context!!)
            dialog.setContentView(R.layout.dialog_select_picture)

            val camera:RelativeLayout = dialog.findViewById(R.id.camera)
            val gallery:RelativeLayout = dialog.findViewById(R.id.gallery)

            camera.setOnClickListener { openCamera() }
            gallery.setOnClickListener { openGallery() }
            dialog.show()
        }

        btn_confirm.setOnClickListener {

            if(username.text.toString() != "" && firstname.text.toString() != "" && lastname.text.toString() != ""
                && mail.text.toString() != "" && company.text.toString() != "" && position.text.toString() != ""){

                //save


                dbManager?.updateProfileAdmin(username.text.toString(),
                    firstname.text.toString(),lastname.text.toString(),
                    mail.text.toString(),company.text.toString(),position.text.toString(),pic.drawToBitmap(),
                    AppPreference.getInstance().getUsername().toString())


                AppPreference.getInstance().setUsername(username.text.toString())

                listener.onSuccess(LandingPage.HOME)


            }else{
                Toast.makeText(context,"Empty",Toast.LENGTH_SHORT).show()
            }



            //edit shared preference

        }






        return v
    }

    fun openCamera() {
        var camera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(camera,CAMERA_REQUEST_CODE)
    }

    private fun openGallery(){
        var gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(gallery,GALLERY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CAMERA_REQUEST_CODE){
            val image : Bitmap = data?.getExtras()?.get("data") as Bitmap
            pic.clearColorFilter()
            pic.setImageBitmap(image)
            dialog.dismiss()
        }else if(requestCode == GALLERY_REQUEST_CODE){
            var selectedImage: Uri = data?.data as Uri
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
            if (selectedImage != null) {
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
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EditProfileFragment2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EditProfileFragment2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }



}