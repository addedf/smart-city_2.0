package com.example.smartcity_20.service.hospital

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.smartcity_20.R
import com.example.smartcity_20.config.kotlin.jump
import com.example.smartcity_20.config.kotlin.tool
import com.example.smartcity_20.config.kotlin.viewBinding
import com.example.smartcity_20.databinding.ActivityHospitalnfoBinding
import com.example.smartcity_20.service.hospital.bean.HospitalnfoBean

class HospitalnfoActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityHospitalnfoBinding::inflate)
    val TAG = "TAG"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.hospitalnfoTb.setOnClickListener {
            finish()
        }
        val id = intent.getIntExtra("id",0)
        tool.apply {
            send("/prod-api/api/hospital/hospital/$id","GET",null,false, HospitalnfoBean::class.java) {
                vb.hospitalnfoLevel.text = it.data.level.toString()
                vb.hospitalnfoName.text = it.data.hospitalName
                vb.hospitalnfoContent.text = it.data.brief
                Glide.with(context)
                    .load(getUrl(it.data.imgUrl)).into(vb.hospitalnfoImg)
                vb.hospitalnfoBtn.setOnClickListener {
                    jump(RegisteredActivity::class.java)
                }
            }
        }
    }
}