package app.by.wildan.workshopkotlin.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.by.wildan.workshopkotlin.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonRegister.setOnClickListener {
            onBackPressed()
        }
    }
}