package com.example.epicture.Network

import com.example.epicture.Constants

class OAuthUrlUtil {
    fun getToken(url : String) : Boolean {
        var listUrl = url.split("?")
        if (listUrl.size <= 1 && listUrl[0] != Constants.urlCallBack)
            return false

        var listState = listUrl[1].split("#")
        if (listState.size <= 1 && listState[0] != "state=" + Constants.state)
            return false

        var listRes = listState[1].split("&")
        if (listRes.size != 6)
            return false

        Constants.accessToken = listRes[0].split("=")[1]
        Constants.expiresIn = listRes[1].split("=")[1]
        Constants.tokenType = listRes[2].split("=")[1]
        Constants.refreshToken = listRes[3].split("=")[1]
        Constants.username = listRes[4].split("=")[1]
        Constants.accounId = listRes[5].split("=")[1]
        return true
    }
}