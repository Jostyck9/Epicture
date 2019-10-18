package com.example.epicture.SplashScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.example.epicture.Login.LoginActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*


class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.epicture.R.layout.activity_splash_screen)

        loginButton.visibility = View.INVISIBLE

        // Using a handler to delay loading the MainActivity
        Handler().postDelayed({

            animLogo()
            name.visibility = View.INVISIBLE

        }, 2000)
    }

    fun login(view: View) {

        // Start activity
        startActivity(Intent(this, LoginActivity::class.java))

        // Animate the loading of new activity
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

        // Close this activity
        //finish()
    }

    private fun animLogo() {

        val duration : Long = 500

        var translationRes = logo.translationY - 300

        logo.animate().setDuration(duration).translationY(translationRes)

        Handler().postDelayed({

            loginButton.visibility = View.VISIBLE

        }, duration)

    }
}
