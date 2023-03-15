package com.example.databases;


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.backendless.Backendless
import com.backendless.BackendlessUser
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import com.example.databases.MainActivity
import com.example.databases.databinding.ActivityRegistrationBinding


class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding

    private lateinit var registrationStartupIntent: Intent

    companion object {
        public final var EXTRA_USERNAME = "com.example.loginandregistration.RegistrationActivity.USERNAME"
        public final var EXTRA_PASSWORD = "com.example.loginandregistration.RegistrationActivity.PASSWORD"
        public final var TAG = "Registration Activity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registrationStartupIntent = getIntent()

        binding.editTextRegistrationUsername.setText(registrationStartupIntent.getStringExtra(MainActivity.EXTRA_USERNAME))
        binding.editTextPasswordRegistrationPassword.setText(registrationStartupIntent.getStringExtra(MainActivity.EXTRA_PASSWORD))

        binding.buttonRegistrationRegister.setOnClickListener {

            val validPasswordAndEmail: Boolean = checkPasswordAndEmail()

            if(validPasswordAndEmail) {

                val user = BackendlessUser()
                user.setProperty("email", binding.editTextRegistrationEmail.text.toString())
                user.setProperty("username", binding.editTextRegistrationUsername.text.toString())
                user.password = binding.editTextPasswordRegistrationPassword.text.toString()

                Backendless.UserService.register(user, object : AsyncCallback<BackendlessUser?> {
                    override fun handleResponse(registeredUser: BackendlessUser?) {
                        // user has been registered and now can login
                        Log.d(TAG, "handleResponse: ${user}")
                    }

                    override fun handleFault(fault: BackendlessFault) {
                        // an error has occurred, the error code can be retrieved with fault.getCode()
                        Log.d(TAG, "handleFault: ${fault.message}")
                    }
                })

            }
            else {
                createInvalidPassOrEmailToast()
            }
        }

    }

    private fun createInvalidPassOrEmailToast() {
        Toast.makeText(this, "Your password or email does not follow the proper format.", Toast.LENGTH_SHORT).show()
    }

    private fun launchMainActivity() {


        val mainActivityIntent: Intent = Intent(this, MainActivity::class.java)

        mainActivityIntent.putExtra(EXTRA_USERNAME, binding.editTextRegistrationUsername.text)
        mainActivityIntent.putExtra(EXTRA_PASSWORD, binding.editTextPasswordRegistrationPassword.text)

        startActivity(mainActivityIntent)


    }

    private fun checkPasswordAndEmail(): Boolean {
        val validPassword: Boolean = RegistrationUtil.validatePassword(binding.editTextPasswordRegistrationPassword.text.toString(), binding.editTextPasswordRegistrationConfirmPassword.text.toString())
        val validEmail: Boolean = RegistrationUtil.validateEmail(binding.editTextRegistrationEmail.text.toString())

        return validPassword && validEmail
    }

}