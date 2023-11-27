package com.example.smartcity_20.service.house

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.smartcity_20.R
import com.example.smartcity_20.config.kotlin.tool
import com.example.smartcity_20.config.kotlin.viewBinding
import com.example.smartcity_20.databinding.ActivityHouseInfoBinding
import com.example.smartcity_20.service.house.bean.HouseInfoBean

class HouseInfoActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityHouseInfoBinding::inflate)
    val TAG = "HouseInfoActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.houseInfoTb.setOnClickListener {
            finish()
        }
        val id = intent.getIntExtra("id",0)
        tool.apply {
            send("/prod-api/api/house/housing/$id","GET",null,false,HouseInfoBean::class.java) {
                vb.houseInfoAddress.text = it.data.address
                vb.houseInfoAreaSize.text = it.data.areaSize.toString()
                vb.houseInfoDescription.text = it.data.description
                vb.houseInfoHouseType.text = it.data.houseType
                vb.houseInfoPrice.text = it.data.price
                vb.houseInfoTel.text = it.data.tel
                try {
                    Glide.with(context).load(getUrl(it.data.pic)).error(getDrawable(R.drawable.fu_img4)).into(vb.houseInfoPic)
                } catch (E:Exception) {
                    Log.e(TAG, "onCreate: ${E.message}")
                }
            }
        }
    }
}