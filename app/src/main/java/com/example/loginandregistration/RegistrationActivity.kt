import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.loginandregistration.databinding.ActivityRegistrationBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}