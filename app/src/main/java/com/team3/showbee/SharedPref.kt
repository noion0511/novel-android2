package com.team3.showbee

import android.content.Context
import android.content.SharedPreferences
import com.team3.showbee.data.entity.Token

object SharedPref {
    val LOGIN_SESSION = "login.session"

    var sharedPref: SharedPreferences? = null
    private var token: Token? = null

    fun openSharedPrep(context: Context): SharedPreferences {
        return context.getSharedPreferences(LOGIN_SESSION, Context.MODE_PRIVATE)
    }

    fun getToken() : Token? {
        if (token == null) {
            token = Token("")
        }
        return token
    }

    fun saveToken(token: Token) {
        this.token = token
        val editor = App.prefs.edit()
        editor.putString("token", token.data)
        editor.apply()
    }
}