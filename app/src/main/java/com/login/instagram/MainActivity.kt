package com.login.instagram

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.login.instagramlogin.appinterface.OnInstLoginListener
import com.login.instagramlogin.helper.InstagramLoginHelper
import com.login.instagramlogin.pojo.LoginSuccessData
import com.login.instagramlogin.utils.LoginOptions

class MainActivity : AppCompatActivity(), OnInstLoginListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    override fun onLoginSuccess(data: LoginSuccessData) {
        Log.e("onLoginSuccess", data.name)
        Log.e("onLoginSuccess", data.socialId)
    }

    override fun onFailure(message: String) {
        Log.e("onFailure", message)
    }

    fun onClickText(view: View) {
        val options = LoginOptions.init()
                .setClientId("1296976470652587")
                .setClientSecret("bcb5057091e933df14a9103b1b83365b")
                .setRedirectionUrl("https://www.netsetsoftware.com/")
                .build(this@MainActivity)
        InstagramLoginHelper.doLogin(this, options)
    }
}