package com.example.smartcity_20.config.kotlin

import androidx.viewbinding.ViewBinding

class GenericAdapter <T:ViewBinding>(
    private val size: Int,
    private val create:() ->T,
)
