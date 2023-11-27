package com.example.smartcity_20.service.hospital

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartcity_20.R
import com.example.smartcity_20.config.kotlin.viewBinding
import com.example.smartcity_20.databinding.ActivityHospitalBinding

class HospitalActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityHospitalBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
    }
}