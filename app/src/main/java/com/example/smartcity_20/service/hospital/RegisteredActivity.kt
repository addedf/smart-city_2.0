package com.example.smartcity_20.service.hospital

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.smartcity_20.R
import com.example.smartcity_20.config.kotlin.GenericAdapter
import com.example.smartcity_20.config.kotlin.tool
import com.example.smartcity_20.config.kotlin.viewBinding
import com.example.smartcity_20.databinding.ActivityRegisteredBinding
import com.example.smartcity_20.databinding.MItemRegListBinding
import com.example.smartcity_20.service.hospital.bean.RegListBean
import okhttp3.internal.notifyAll
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.log

class RegisteredActivity : AppCompatActivity(),RegDialog.OnItemClickListener {
    private val vb by viewBinding(ActivityRegisteredBinding::inflate)
    val TAG = "RegisteredActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.regTb.setOnClickListener {
            finish()
        }
        vb.regAdd.setOnClickListener {
            RegDialog(this,this).show()
        }
        loadList()
    }

    private fun loadList() {
        tool.apply {
            send("/prod-api/api/hospital/patient/list","GET",null,true, RegListBean::class.java) {
                val data = it.rows
                vb.regList.adapter = GenericAdapter(it.rows.size,
                    { MItemRegListBinding.inflate(layoutInflater) }) { binding,position->
                    binding.mItemRegListAddress.text = it.rows[position].address
                    binding.mItemRegListName.text = it.rows[position].name
                    binding.mItemRegListSex.text = when(it.rows[position].sex) {
                        "1" -> "女"
                        else -> "男"
                    }
                    binding.root.setOnClickListener {
                        val intent = Intent(context,RegInfoActivity::class.java)
                        intent.putExtra("id",data[position].id)
                        intent.putExtra("name",data[position].name)
                        intent.putExtra("cardId",data[position].cardId)
                        intent.putExtra("tel",data[position].tel)
                        intent.putExtra("sex",data[position].sex)
                        intent.putExtra("address",data[position].address)
                        startActivity(intent)
                    }
                }
                vb.regList.layoutManager = LinearLayoutManager(context)
            }
        }
    }

    override fun userInfo(name: String, sex: String, cardId: String, tel: String, address: String) {
        val currentDate = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formattedDate = currentDate.format(formatter)
        val data = """
            {
            "address": "$address",
            "birthday": "$formattedDate",
            "cardId": "$cardId",
            "name": "$name",
            "sex": "$sex",
            "tel": "$tel"
            }
        """.trimIndent()
        tool.apply {
            send("/prod-api/api/hospital/patient","POST",data,true) {
                if (it.contains("操作成功")) {
                    loadList()
                    Toast.makeText(context,"添加成功", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, JSONObject(it).getString("msg"), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}