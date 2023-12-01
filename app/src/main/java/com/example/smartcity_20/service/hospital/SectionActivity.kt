package com.example.smartcity_20.service.hospital

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartcity_20.R
import com.example.smartcity_20.config.kotlin.GenericAdapter
import com.example.smartcity_20.config.kotlin.tool
import com.example.smartcity_20.config.kotlin.viewBinding
import com.example.smartcity_20.databinding.ActivitySectionBinding
import com.example.smartcity_20.databinding.MItemSectionListBinding
import com.example.smartcity_20.service.hospital.bean.SectionBean

class SectionActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivitySectionBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.sectionTb.setOnClickListener {
            finish()
        }
        tool.apply {
            send("/prod-api/api/hospital/category/list","GET",null,false,SectionBean::class.java) {
                val data = it.rows
                vb.sectionList.adapter = GenericAdapter(it.rows.size,
                    { MItemSectionListBinding.inflate(layoutInflater) }) { binding,position->
                    binding.mItemSectionCategoryName.text = "门诊" + it.rows[position].categoryName
                    binding.mItemSectionMoney.text = "挂号费:" + it.rows[position].money
                    binding.root.setOnClickListener {
                        val intent = Intent(context,SectionInfoActivity::class.java)
                        intent.apply {
                            putExtra("id",data[position].id)
                            putExtra("type",data[position].type)
                            putExtra("money",data[position].money)
                            putExtra("categoryName",data[position].categoryName)
                        }
                        startActivity(intent)
                    }
                }
                vb.sectionList.layoutManager = LinearLayoutManager(context)
            }
        }
    }
}