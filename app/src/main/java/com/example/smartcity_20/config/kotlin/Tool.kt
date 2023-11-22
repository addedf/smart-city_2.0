package com.example.smartcity_20.config.kotlin

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.smartcity_20.databinding.BannerViewBinding
import com.google.android.material.snackbar.Snackbar
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.lang.Exception

// TODO("记不住")
class Tool(val context: Context) {
    companion object {
        const val TAG = "Tool"
        val handler = android.os.Handler()
        val client = OkHttpClient()
    }

    val sp = context.getSharedPreferences("data", Context.MODE_PRIVATE)

    fun get(key: String): String {
        return sp.getString(key, "").toString()
    }

    fun set(key: String, value: String) {
        sp.edit().putString(key, value).apply()
    }

    fun getUrl(url: String): String {
        return "http://${get("server")}:${get("port")}$url"
    }

    fun send(
        url: String,
        method: String,
        body: RequestBody?,
        auth: Boolean,
        then: (String) -> Unit
    ) {
        try {
            val req = Request.Builder()
            req.url(getUrl(url))
            req.method(method, body)
            if (auth) {
                req.addHeader("Authorization", "Bearer ${get("token")}")
            }
            client.newCall(req.build()).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e(TAG, "请求失败：${e.message}")
                }

                override fun onResponse(call: Call, response: Response) {
                    val str = response.body?.string()
                    handler.post {
                        if (str != null) {
                            then(str)
                        }
                    }
                }

            })
        } catch (e: Exception) {
            Log.e(TAG, "send: 解析错误${e.message}")
        }
    }

    fun checkToken(fn: (Boolean) -> Unit) {
        send("/prod-api/api/common/user/getInfo", "GET", null, true) {
            try {
                set("userId", JSONObject(it).getJSONObject("user").getInt("userId").toString())
            } catch (e: Exception) {
                Log.e(TAG, "checkToken: 未登录/写入失败")
                set("userId", "")
            }
            fn(it.contains("操作成功"))
        }
    }

    fun snackBar(view: View, msg: String, btn: String, action: () -> Unit) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).setAction(btn) { action() }.show()
    }

    fun setBanner(view:ViewPager2,list:List<String>) {
        view.adapter = GenericAdapter(Int.MAX_VALUE,
            { BannerViewBinding.inflate(LayoutInflater.from(context)) }) { binding, position->
            binding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            Glide.with(context).load(getUrl(list[position % list.size])).centerCrop().into(binding.root)
        }
        view.postDelayed(object : Runnable {
            override fun run() {
                view.currentItem += 1
                view.postDelayed(this,3000)
            }
        },3000)
    }
}