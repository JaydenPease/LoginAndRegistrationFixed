package com.example.loginandregistration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.loginandregistration.databinding.ActivityMainBinding

abstract class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var username: String? = null
    private var password: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonMainSignUp.setOnClickListener {

        }
    }
}