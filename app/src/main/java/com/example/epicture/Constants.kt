package com.example.epicture

class Constants {
    companion object {
        val clientID = BuildConfig.IMGUR_API_KEY
        val urlCallBack = "https://app.getpostman.com/oauth2/callback"
        val state = "Epicture"
        var accessToken = ""
        var refreshToken = ""
        var expiresIn = ""
        var tokenType = ""
        var username = ""
        var accounId = ""
    }
}