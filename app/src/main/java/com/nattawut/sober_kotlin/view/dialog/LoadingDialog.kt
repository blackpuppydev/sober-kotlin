package com.nattawut.sober_kotlin.view.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.TextView
import com.nattawut.sober_kotlin.R

class LoadingDialog(var context: Context) {

    lateinit var dialog:Dialog

    public fun showDialog(title:String){

        dialog = Dialog(context)
        dialog.setContentView(R.layout.load_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        var titleDialog:TextView =  dialog.findViewById(R.id.titleDialog)
        titleDialog.text = title
        dialog.create()
        dialog.show()

    }

    public fun hideDialog(){
        dialog.dismiss()
    }


}