package com.nattawut.sober_kotlin.activity

import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.nattawut.sober_kotlin.R

class ProfileSelectPhotoActivity : BaseActivity() {


    lateinit var btn_back: ImageButton
    lateinit var pic: ImageView
    lateinit var dialog: Dialog
    lateinit var btn_photo:RelativeLayout

    var CAMERA_PERM_CODE = 101
    var GALLERY_PERM_CODE = 102
    var CAMERA_REQUEST_CODE = 103
    var GALLERY_REQUEST_CODE = 104

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_select_photo)


        initView()

    }



    private fun initView(){

        btn_next.setOnClickListener {
            startActivity(Intent(this,SelectTypeActivity::class.java))
        }

        btn_back = findViewById(R.id.btn_back)
        pic = findViewById(R.id.pic)
        btn_photo = findViewById(R.id.btn_photo)

        btn_back.setOnClickListener{
            finish()
            super.onBackPressed()
        }

        btn_photo.setOnClickListener {

            dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog_select_picture)

            var btn_camera:RelativeLayout = dialog.findViewById(R.id.camera)
            var btn_gallery:RelativeLayout = dialog.findViewById(R.id.gallery)

            btn_camera.setOnClickListener {
//                Toast.makeText(this,"camera",Toast.LENGTH_SHORT).show()
                askPermission(android.Manifest.permission.CAMERA,CAMERA_PERM_CODE)

            }

            btn_gallery.setOnClickListener {
//                Toast.makeText(this,"gallery",Toast.LENGTH_SHORT).show()
                askPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE,GALLERY_PERM_CODE)
            }

            dialog.show()
        }


    }



    private fun askPermission(permission :String,requestCode: Int){ //add permission
        if(ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(permission),requestCode)
        }else{
            when(requestCode){
                CAMERA_PERM_CODE -> openCamera()
                GALLERY_PERM_CODE -> openGallery()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(grantResults.isEmpty() && grantResults[0] != PackageManager.PERMISSION_GRANTED){
            //permission failed
        }else{
            when(requestCode){
                CAMERA_PERM_CODE -> openCamera()
                GALLERY_PERM_CODE -> openGallery()
            }
        }
    }

    private fun openCamera() {
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
            var image : Bitmap = data?.getExtras()?.get("data") as Bitmap
            pic.setImageBitmap(image)
            dialog.dismiss()
        }else if(requestCode == GALLERY_REQUEST_CODE){
            var selectedImage: Uri = data?.data as Uri
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
            if (selectedImage != null) {
                val cursor: Cursor? = contentResolver.query(
                    selectedImage,
                    filePathColumn, null, null, null
                )
                if (cursor != null) {
                    cursor.moveToFirst()
                    val columnIndex: Int = cursor.getColumnIndex(filePathColumn[0])
                    val picturePath: String = cursor.getString(columnIndex)
                    pic.setImageBitmap(BitmapFactory.decodeFile(picturePath))
                    cursor.close()
                    dialog.dismiss()
                }
            }
        }
    }

}