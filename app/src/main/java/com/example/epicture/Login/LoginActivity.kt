package com.example.epicture.Login

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.example.epicture.Constants
import com.example.epicture.MainActivity
import com.example.epicture.Network.OAuthUrlUtil
import com.example.epicture.R
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.http.Url

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_login)

        val settings = webViewLogin.settings
        settings.javaScriptEnabled = true
        webViewLogin.webViewClient = object: WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                pb_loadingLogin.visibility = View.INVISIBLE
            }
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if (url != null && OAuthUrlUtil().getToken(url)) {
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                }
            }
        }
        webViewLogin.loadUrl("https://api.imgur.com/oauth2/authorize?client_id=" + Constants.clientID + "&response_type=token&state=" + Constants.state)
    }
}
