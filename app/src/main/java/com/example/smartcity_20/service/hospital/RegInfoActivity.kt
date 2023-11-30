package com.example.smartcity_20.service.hospital

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import com.example.smartcity_20.R
import com.example.smartcity_20.config.kotlin.tool
import com.example.smartcity_20.config.kotlin.viewBinding
import com.example.smartcity_20.databinding.ActivityRegInfoBinding
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class RegInfoActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityRegInfoBinding::inflate)
    val TAG = "RegInfoActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        val id = intent.getIntExtra("id",0)
        val name = intent.getStringExtra("name")
        val cardId = intent.getStringExtra("cardId")
        val tel = intent.getStringExtra("tel")
        val sex = intent.getStringExtra("sex")
        val address = intent.getStringExtra("address")
        vb.regInfoName.setText(name)
        vb.regInfoCardId.setText(cardId)
        vb.regInfoTel.setText(tel)
        vb.regInfoAddress.setText(address)
        when(sex) {
            "0" -> vb.regInfoMan.isChecked = true
            "1" -> vb.regInfoWoman.isChecked = true
            "男" -> vb.regInfoMan.isChecked = true
            "女" -> vb.regInfoWoman.isChecked = true
        }
        vb.regInfoBtn.setOnClickListener {
            var sex = ""
            vb.RegInfoRg.setOnCheckedChangeListener { _, checkedId ->
                val selectedBtn = findViewById<RadioButton>(checkedId)
                if (selectedBtn.isChecked) {
                    sex = when(selectedBtn.text.toString()) {
                        "女" -> "1"
                        else -> "0"
                    }
                }
            }
            val currentDateTime = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val formattedDate = currentDateTime.format(formatter)
            val data = """
            {
           "id": $id,
            "address": "${vb.regInfoAddress.text}",
            "birthday": "$formattedDate",
            "cardId": "${vb.regInfoCardId.text}",
            "name": "${vb.regInfoName.text}",
            "sex": "$sex",
            "tel": "${vb.regInfoTel.text}"
            }
        """.trimIndent()
            tool.apply {
                send("/prod-api/api/hospital/patient","PUT",data,true) {
                    if (it.contains("操作成功")) {
                        Toast.makeText(context,"修改成功", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, JSONObject(it).getString("msg"), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}