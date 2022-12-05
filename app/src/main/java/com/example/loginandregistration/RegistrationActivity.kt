import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginandregistration.MainActivity
import com.example.loginandregistration.RegistrationUtil
import com.example.loginandregistration.databinding.ActivityRegistrationBinding


class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding

    private lateinit var registrationStartupIntent: Intent

    companion object {
        public final var EXTRA_USERNAME = "com.example.loginandregistration.RegistrationActivity.USERNAME"
        public final var EXTRA_PASSWPRD = "com.example.loginandregistration.RegistrationActivity.PASSWORD"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registrationStartupIntent = getIntent()

        binding.editTextRegistrationUsername.setText(registrationStartupIntent.getStringExtra(MainActivity.EXTRA_USERNAME))
        binding.editTextPasswordRegistrationPassword.setText(registrationStartupIntent.getStringExtra(MainActivity.EXTRA_PASSWORD))

        binding.buttonRegistrationRegister.setOnClickListener {

            var validPasswordAndEmail: Boolean = checkPasswordAndEmail()

            if(validPasswordAndEmail) {launchMainActivity()}
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
        mainActivityIntent.putExtra(EXTRA_PASSWPRD, binding.editTextPasswordRegistrationPassword.text)

        startActivity(mainActivityIntent)


    }

    private fun checkPasswordAndEmail(): Boolean {
        var validPassword: Boolean = RegistrationUtil.validatePassword(binding.editTextPasswordRegistrationPassword.text.toString(), binding.editTextPasswordRegistrationConfirmPassword.text.toString())
        var validEmail: Boolean = false

        return validPassword && validEmail
    }

}