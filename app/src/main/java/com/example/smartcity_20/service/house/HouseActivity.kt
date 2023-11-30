    package com.example.smartcity_20.service.house

import android.content.Intent
import android.graphics.drawable.ClipDrawable.HORIZONTAL
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.smartcity_20.R
import com.example.smartcity_20.config.kotlin.GenericAdapter
import com.example.smartcity_20.config.kotlin.tool
import com.example.smartcity_20.config.kotlin.viewBinding
import com.example.smartcity_20.databinding.ActivityHouseBinding
import com.example.smartcity_20.databinding.MItemHouseListBinding
import com.example.smartcity_20.databinding.MItemHouseTabBinding
import com.example.smartcity_20.me.LoginActivity
import com.example.smartcity_20.service.house.bean.HouseTabTypeBean
import com.example.smartcity_20.service.house.bean.HouseTypeBean

class HouseActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityHouseBinding::inflate)
    var filter = ""
    val TAG = "HouseActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        tool.checkToken {
            if (it) {
                loadTab()
                loadList("二手")
            } else {
                tool.snackBar(vb.root,"未登录","去登陆") {
                    startActivity(Intent(this,LoginActivity::class.java))
                }
            }
        }
        vb.houseSearch.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filter = query!!
                loadList(" ")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean = true
        })
    }

    private fun loadTab() {
        val list = ArrayList<HouseTabTypeBean>()
        list.add(HouseTabTypeBean(R.drawable.house_1, "二手", 1))
        list.add(HouseTabTypeBean(R.drawable.house_2, "租房", 2))
        list.add(HouseTabTypeBean(R.drawable.house_3, "楼盘", 3))
        list.add(HouseTabTypeBean(R.drawable.house_4, "中介", 4))
        tool.apply {
            vb.houseTab.adapter = GenericAdapter(list.size,
                { MItemHouseTabBinding.inflate(layoutInflater) }) { binding,position ->
                binding.mItemHouseTabText.text = list[position].str
                binding.mItemHouseTabImg.setImageResource(list[position].img)
                binding.root.setOnClickListener {
                    loadList(list[position].str)
                }
            }
            vb.houseTab.layoutManager = GridLayoutManager(context,4)
        }
    }

    private fun loadList(type: String?) {
        tool.apply {
            send("/prod-api/api/house/housing/list","GET",null,false, HouseTypeBean::class.java) {
                val list = mutableListOf<HouseTypeBean.RowsBean>()
                val filterHouse = if (type.equals(" ")) {
                    it.rows
                } else {
                    it.rows.filter { it.houseType == type }
                }

                for (i in it.rows) {
                    i.apply {
                        try {
                            if (price.contains(filter) || sourceName.contains(filter) || areaSize.toString().contains(filter)) {
                                list.add(i)
                            }
                        } catch (e:Exception) {
                            Log.e(TAG, "${e.message}")
                        }
                    }
                }
                vb.houseList.adapter = GenericAdapter(list.size,
                    { MItemHouseListBinding.inflate(layoutInflater) }) { binding,position->
                    try {
                        binding.mItemHouseListAddress.text = list[position].address
                        binding.mItemHouseListPrice.text = list[position].price
                        binding.mItemHouseListSourceName.text = list[position].sourceName
                        binding.root.setOnClickListener {
                            val intent = Intent(context,HouseInfoActivity::class.java)
                            intent.putExtra("id",list[position].id)
                            startActivity(intent)
                        }
                        Glide.with(context).load(getUrl(list[position].pic))
                            .error(getDrawable(R.drawable.fu_img4))
                            .into(binding.mItemHouseListPic)
                    } catch (e:Exception) {
                        Log.e(TAG, "loadList: ${e.message }")
                    }
                }
                vb.houseList.layoutManager = LinearLayoutManager(context)
            }
        }
    }
}