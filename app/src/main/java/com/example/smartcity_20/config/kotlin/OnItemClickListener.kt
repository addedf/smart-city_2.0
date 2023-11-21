package com.example.smartcity_20.config.kotlin

interface  OnItemClickListener {
    fun onitemclick(id:Int)
    fun onUserInfo(name:String,sex:String,userid:String?,phone:String,address:String)
    fun onCarinfo(engineNo:String,phoneNo: String,type:String)
}