package com.example.smartcity_20.service.bean

data class ServiceBean(val rows: List<Data>) {
    data class Data(val imgUrl: String, val serviceName: String, val sort: Int,val id : Int)
}