package com.example.smartcity_20.service.house

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartcity_20.R
import com.example.smartcity_20.config.kotlin.viewBinding
import com.example.smartcity_20.databinding.ActivityHouseBinding

class HouseActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityHouseBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
    }
}