package app.by.wildan.workshopkotlin.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.by.wildan.workshopkotlin.R
import app.by.wildan.workshopkotlin.main.MainActivity
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        buttonLogin.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }

        buttonRegister.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}