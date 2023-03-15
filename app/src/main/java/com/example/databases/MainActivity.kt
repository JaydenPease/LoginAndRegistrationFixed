//This project is for:
//Overview of BaaS and CRUD
// Baas - Backend as a Service, host our database, handle emailing uses, registration, etc
// one we're using: Backendless
//CRUD - Basic database Operations (Create, Read/retrieve, Update, Delete/destroy)

//Doing: Tracking how much money is owed to you:
//Things to keep track of: Name, Amount, Date lent, Why they owe you money, how much repaid, dates of payments, credit score, is it fully repaid

package com.example.databases


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.backendless.Backendless
import com.backendless.BackendlessUser
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import com.example.databases.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    companion object {
        public final var EXTRA_USERNAME: String = "com.example.loginandregistration.MainActivity.USERNAME"
        public final var EXTRA_PASSWORD: String = "com.example.loginandregistration.MainActivity.PASSWORD"

        public final var TAG: String = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Backendless.initApp(this, Constants.APP_ID, Constants.API_KEY)


        binding.buttonMainSignUp.setOnClickListener {


            launchRegistrationActivity()

        }

        binding.buttonMainLogIn.setOnClickListener {

            Backendless.UserService.login(
                binding.editTextMainUsername.text.toString(),
                binding.editTextPasswordMainPassword.text.toString(),
                object : AsyncCallback<BackendlessUser?> {
                    override fun handleResponse(user: BackendlessUser?) {
                        // user has been logged in
                        Log.d(TAG, "handleResponse: ${user?.getProperty("username")} has logged in")
                    }

                    override fun handleFault(fault: BackendlessFault) {
                        // login failed, to get the error code call fault.getCode()
                        Log.d(TAG, "handleFault: ${fault.message}")
                    }
                })

        }
    }



    private fun launchRegistrationActivity() {

        val registrationIntent: Intent = Intent(this, RegistrationActivity::class.java)

        registrationIntent.putExtra(EXTRA_USERNAME, binding.editTextMainUsername.text)
        registrationIntent.putExtra(EXTRA_PASSWORD, binding.editTextPasswordMainPassword.text)

        startActivity(registrationIntent)
    }
}