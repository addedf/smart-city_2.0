package com.example.smartcity_20.service.hospital

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.RadioButton
import com.example.smartcity_20.databinding.RegDialogBinding

class RegDialog(context: Context, private val listener : OnItemClickListener) : Dialog(context) {
    lateinit var vb : RegDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        vb = RegDialogBinding.inflate(layoutInflater)
        setContentView(vb.root)
        var sex = ""
        vb.regDialogRg.setOnCheckedChangeListener { _, checkedId ->
            val currentBtn = findViewById<RadioButton>(checkedId)
            if (currentBtn.isChecked) {
                sex = when(currentBtn.text.toString()) {
                    "女" -> "1"
                    else -> "0"
                }
            }
        }
        vb.regDialogOk.setOnClickListener {
            val name = vb.regDialogName.text.toString()
            val address = vb.regDialogAddress.text.toString()
            val cardId = vb.regDialogCardId.text.toString()
            val tel = vb.regDialogTel.text.toString()
            listener.userInfo(name,sex,cardId,tel,address)
            dismiss()
        }
        vb.regDialogErr.setOnClickListener {
            dismiss()
        }
    }

    interface OnItemClickListener {
        fun userInfo(name : String,sex : String,cardId : String,tel : String,address:String)
    }
}