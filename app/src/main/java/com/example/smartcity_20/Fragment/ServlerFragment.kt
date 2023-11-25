package com.example.smartcity_20.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.smartcity_20.config.kotlin.*
import com.example.smartcity_20.databinding.FragmentServlerBinding
import com.example.smartcity_20.databinding.MItemServiceListBinding
import com.example.smartcity_20.service.bean.ServiceBean
import com.example.smartcity_20.service.hospital.HospitalActivity
import com.example.smartcity_20.service.house.HouseActivity
import com.example.smartcity_20.service.legal.LegalActivity
import com.example.smartcity_20.service.pethospital.PetHospitalActivity
import com.example.smartcity_20.service.takeout.TakeOutActivity
import java.lang.Exception

class ServlerFragment : Fragment() {
    //IP = "124.93.196.45"
//    private val vb by viewBinding { FragmentServlerBinding.inflate(layoutInflater) }
    val TAG = "ServlerFragment"
    private val vb by viewBinding(FragmentServlerBinding::inflate)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loadService()
        tool.apply {
//            判断是否为空 然后默认填入端口和ip
            if (get("server").isEmpty() || get("port").isEmpty()) {
                set("server","124.93.196.45")
                set("port","10001")
            }
        }
        return vb.root
    }

    private fun loadService() {
        tool.apply {
            send("/prod-api/api/service/list", "GET", null, false) {
                val bean = g.fromJson(it, ServiceBean::class.java)
                val localLawyer = ServiceBean.Data(
                    imgUrl = "/prod-api/profile/upload/image/2023/02/13/dbc84fa3-05ef-476a-8268-422b09eb4866.png",
                    serviceName = "法律咨询",
                    sort = 1,
                    id = 0
                )
                val modifiedServiceBean = bean.copy(
                    rows = bean.rows + localLawyer
                )
                vb.serviceList.adapter = GenericAdapter(modifiedServiceBean.rows.size,
                    { MItemServiceListBinding.inflate(layoutInflater) }) { binding,position->
                    binding.mItemServiceText.text = modifiedServiceBean.rows[position].serviceName
                    try {
                        Glide.with(context).load(getUrl(modifiedServiceBean.rows[position].imgUrl)).into(binding.mItemServiceImg)
                    } catch (e:Exception) {
                        Log.e(TAG, "loadService: ${e.message}")
                    }
                    binding.root.setOnClickListener {
                        when(modifiedServiceBean.rows[position].serviceName) {
                            "门诊预约" -> jump(HospitalActivity::class.java)
                            "法律咨询" -> jump(LegalActivity::class.java)
                            "找房子" -> jump(HouseActivity::class.java)
                            "外卖订餐" -> jump(TakeOutActivity::class.java)
                            "宠物医院" -> jump(PetHospitalActivity::class.java)
                            else -> Toast.makeText(context,"待开发",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                vb.serviceList.layoutManager = GridLayoutManager(context,5)
            }
        }
    }
}