package com.example.smartcity_20.config.kotlin

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import java.util.logging.Handler

class Tool2(val context: Context) {
    //companion 相当于 static
    companion object {
        const val TAG = "tool"
        val hander = android.os.Handler()
        val okHttpClient = OkHttpClient()
        val gson = Gson()
    }

    val sp = context.getSharedPreferences("login", Context.MODE_PRIVATE)

    fun get(key: String): String {
        return sp.getString(key, "").toString();
    }

    fun set(key: String, values: String) {
        sp.edit().putString(key, values).apply()
    }

    fun <T> send(
        url: String,
        method: String,
        json: String?,
        token: Boolean,
        clazz: Class<T>,
        then: (T) -> Unit
    ) {
        try {
            val body = json?.toRequestBody("application/json".toMediaTypeOrNull())
            val builder = Request.Builder()
            builder.method(method, body)
            builder.url(url)
            if (token) {
                builder.addHeader("Authorization", "${get("TOKEN")}")
            }
            okHttpClient.newCall(builder.build()).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e(TAG,"请求失败${e.message}");
                }

                override fun onResponse(call: Call, response: Response) {
                    val str = response.body.toString()
                    val bean = gson.fromJson(str,clazz)
                    hander.post{
                        then(bean)
                    }
                }
            })
        } catch (e: Exception) {
            Log.e(Tool.TAG, "send: 解析错误${e.message}")
        }
    }
}