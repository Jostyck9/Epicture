package com.example.epicture


import com.example.epicture.Network.OAuthUrlUtil
import org.junit.Test

import org.junit.Assert.*

class OAuthUrlUnitTest {
    @Test
    fun getTokenIsFalseWithRandomMessage() {
        assertFalse(OAuthUrlUtil().getToken("wololo"))
    }
    @Test
    fun getAnotherTokenIsFalse() {
        assertFalse(OAuthUrlUtil().getToken("https://www.google.fr"))
    }
    @Test
    fun getRandomTokenIsFalse() {
        assertFalse(OAuthUrlUtil().getToken("https://app.getpostman.com/oauth2/callbackstate=Epicture#access_token=6ae3a3cfe1d053361f0cfdaa1cf9cf1814602d0a&expires_in=315360000&token_type=bearer&refresh_token=b6f64013073c4271518512705102ef93461b0362&account_username=Jostyck9&account_id=115193735"))
    }
    @Test
    fun getTokenIsTrue() {
        assertTrue(OAuthUrlUtil().getToken("https://app.getpostman.com/oauth2/callback?state=Epicture#access_token=6ae3a3cfe1d053361f0cfdaa1cf9cf1814602d0a&expires_in=315360000&token_type=bearer&refresh_token=b6f64013073c4271518512705102ef93461b0362&account_username=Jostyck9&account_id=115193735"))
    }
}