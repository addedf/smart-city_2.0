package com.example.smartcity_20.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.smartcity_20.R
import com.example.smartcity_20.config.kotlin.g
import com.example.smartcity_20.config.kotlin.tool
import com.example.smartcity_20.config.kotlin.viewBinding
import com.example.smartcity_20.databinding.FragmentHelpBinding
import com.example.smartcity_20.help.bean.HelpBannerBean

class HelpFragment : Fragment() {
    private val vb by viewBinding(FragmentHelpBinding::inflate)
    val TAG = "HelpFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loadBanner()
        return vb.root

    }

    private fun loadBanner() {
        tool.apply {
            send("/prod-api/api/gov-service-hotline/ad-banner/list", "GET", null, true) {
                val data = g.fromJson(it, HelpBannerBean::class.java).data
                val list = mutableListOf<String>()
                for (i in data.indices) {
                    list.add(data[i].imgUrl)
                }
                setBanner(vb.helpBanner, list)

            }
        }
    }
}