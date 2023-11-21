package com.example.smartcity_20.config.kotlin

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.res.Resources
import android.text.Layout
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.gson.Gson

val Activity.tool:Tool get() =  Tool(this)
val Dialog.tool:Tool get() = Tool(context)
val Fragment.tool:Tool get() =  context?.let { Tool(it) }!!

fun <A : Activity> Activity.jump(act:Class<A>) {
    startActivity(Intent(this,act))
}

fun <A : Activity> Fragment.jump(act:Class<A>) {
    startActivity(Intent(context,act))
}

inline fun <reified T : ViewBinding> Activity.viewBinding(noinline inflater:(LayoutInflater) -> T) =
    lazy(LazyThreadSafetyMode.NONE) {
        inflater.invoke(layoutInflater)
    }
inline fun <reified T : ViewBinding> Fragment.viewBinding(noinline inflater:(LayoutInflater) -> T) =
    lazy(LazyThreadSafetyMode.NONE) {
        inflater.invoke(layoutInflater)
    }

val g:Gson get() = Gson()
val Int.dp:Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()