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

    companion object {
        public final var EXTRA_USERNAME: String = "com.example.loginandregistration.MainActivity.USERNAME"
        public final var EXTRA_PASSWORD: String = "com.example.loginandregistration.MainActivity.PASSWORD"
    }

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

        registrationIntent.putExtra(EXTRA_USERNAME, binding.editTextMainUsername.text)
        registrationIntent.putExtra(EXTRA_PASSWORD, binding.editTextPasswordMainPassword.text)

        startActivity(registrationIntent)
    }
}