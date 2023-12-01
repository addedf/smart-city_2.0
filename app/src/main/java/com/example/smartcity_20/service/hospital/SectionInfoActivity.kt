package com.example.smartcity_20.service.hospital

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartcity_20.R
import com.example.smartcity_20.config.kotlin.GenericAdapter
import com.example.smartcity_20.config.kotlin.tool
import com.example.smartcity_20.config.kotlin.viewBinding
import com.example.smartcity_20.databinding.ActivitySectionInfoBinding
import com.example.smartcity_20.databinding.MItemSectionInfoListBinding
import com.example.smartcity_20.service.hospital.bean.SectionInfoListBean
import com.google.android.material.tabs.TabLayout

class SectionInfoActivity : AppCompatActivity() {
//    type=1是专家，2是普通
    private val vb by viewBinding(ActivitySectionInfoBinding::inflate)
    val TAG = "SectionInfoActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.sectionInfoTab.setOnClickListener {
            finish()
        }
        val id = intent.getIntExtra("id",0)
        val type = intent.getStringExtra("type")
        val money = intent.getIntExtra("money",0)
        val categoryName = intent.getStringExtra("categoryName")
        vb.sectionInfoText.text = categoryName
        loadTab()
        loadList(2)
    }

    private fun loadTab() {
        val list = mutableListOf<String>()
        list.add("普通号")
        list.add("专家号")
        for (i in list.indices) {
            val newTab = vb.sectionInfoTab.newTab()
            newTab.text = list[i]
            vb.sectionInfoTab.addTab(newTab)
        }
        vb.sectionInfoTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    when(tab.position) {
                        0 -> {
                            loadList(2)
                        }
                        1 -> {
                            loadList(1)
                        }
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    private fun loadList(position: Int) {
        tool.apply {
            send("/prod-api/api/hospital/reservation/list","GET",null,true,
                SectionInfoListBean::class.java
            ) {
                vb.sectionInfoList.adapter = GenericAdapter(it.rows.size,
                    { MItemSectionInfoListBinding.inflate(layoutInflater) }) { binding,position->
                    binding.mItemSectionInfoListPatientName.text = "预约人:" + it.rows[position].patientName
                    binding.mItemSectionInfoListReserveTime.text = "预约时间:" + it.rows[position].reserveTime
                    binding.mItemSectionInfoListType.text = "预约类型:" + when(it.rows[position].type) {
                        "1" -> {
                            "专家号"
                        }
                        else -> "普通号"
                    }
                }
                vb.sectionInfoList.layoutManager = LinearLayoutManager(context)
            }
        }
    }
}