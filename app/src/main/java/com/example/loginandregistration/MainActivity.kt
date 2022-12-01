package com.example.loginandregistration

import RegistrationActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.loginandregistration.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var username: String? = null
    private var password: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonMainSignUp.setOnClickListener {

            launchRegistrationActivity()

        }
    }

    private fun launchRegistrationActivity() {
        val registrationIntent: Intent = Intent(this, RegistrationActivity::class.java)
        startActivity(registrationIntent)
    }
}