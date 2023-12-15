package com.example.smartcity_20.service.hospital

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.smartcity_20.R
import com.example.smartcity_20.config.java.OkHttpRequest
import com.example.smartcity_20.config.kotlin.*
import com.example.smartcity_20.databinding.ActivityHospitalBinding
import com.example.smartcity_20.databinding.LItemNewsBinding
import com.example.smartcity_20.me.LoginActivity
import com.example.smartcity_20.service.hospital.bean.HospitalBean
import com.example.smartcity_20.service.hospital.bean.HouseLisetBean
import java.lang.Exception

class HospitalActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityHospitalBinding::inflate)
    val TAG = "HospitalActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.hospitalTb.setOnClickListener {
            finish()
        }
        tool.checkToken {
            if (it) {
                loadBanner()
                loadList()
            } else {
                tool.snackBar(vb.root,"未登录","去登陆") {
                    startActivity(Intent(this,LoginActivity::class.java))
                }
            }
        }
    }

    private fun loadList() {
        tool.apply {
            send("/prod-api/api/hospital/hospital/list","GET",null,false) {
                val data = g.fromJson(it,HouseLisetBean::class.java).rows
                vb.hospitalList.adapter = GenericAdapter(data.size,
                    { LItemNewsBinding.inflate(layoutInflater) }) { binding,position->
                    binding.root.setOnClickListener {
                        val intent = Intent(context,HospitalnfoActivity::class.java)
                        intent.putExtra("id",data[position].id)
                        startActivity(intent)
                    }
                    try {
                        Glide.with(context).load(getUrl(data[position].imgUrl)).into(binding.imgUrl)
                    } catch (e:Exception) {
                        Log.e(TAG, "loadList: ${e.message}")
                    }
                   binding.title.text = data[position].hospitalName
                    binding.likeNum.text = "星级:" + data[position].level.toString()
                    binding.content.text = data[position].brief
                }
                vb.hospitalList.layoutManager = LinearLayoutManager(context)
            }
        }
    }

    private fun loadBanner() {
        tool.apply {
            send("/prod-api/api/hospital/banner/list","GET",null,false,HospitalBean::class.java) {
                val list = mutableListOf<String>()
                for (i in it.data.indices) {
                    list.add(it.data[i].imgUrl)
                }
               /// setBanner(vb.hospitalBanner,list)
                OkHttpRequest.doMapRequst(vb.hospitalBanner,list,context)
            }
        }

    }
}